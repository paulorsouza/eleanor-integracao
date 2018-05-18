package br.com.eleanor.client.firebase

import br.com.eleanor.data.FirebaseKeyData
import br.com.eleanor.data.SetorData
import br.com.eleanor.data.SetoresData
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class SetorClient {

    val BASE_URL = "https://planeleanor.firebaseio.com/setores"
    val SETOR_URL = "${BASE_URL}.json"

    fun urlById(id: String): String {
        return "${BASE_URL}/${id}.json"
    }

    fun listSetores(): Map<String, SetorData>? {
        val setorUrl = SETOR_URL
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<SetorData>(headers)
        val restTemplate = RestTemplate()
        try{
            val response = restTemplate.exchange<SetoresData>(setorUrl, HttpMethod.GET, entity, SetoresData::class.java)
            val SetoresData = response.body
            return SetoresData.getSetores()
        } catch(ex: Exception){
            println(ex.message)
            return null
        }
    }

    fun addSetor(codigo: String, nome: String): FirebaseKeyData {
        val setor = SetorData(codigo, nome)
        val headers = HttpHeaders()
        headers.accept = arrayListOf(MediaType.APPLICATION_JSON)
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<SetorData>(setor, headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<FirebaseKeyData>(SETOR_URL,
                HttpMethod.POST, entity, FirebaseKeyData::class.java)
        return response.body
    }

    fun deleteSetor(id: String): HttpStatus? {
        val url = urlById(id)
        val headers = HttpHeaders()
        val entity = HttpEntity<String>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<String>(url, HttpMethod.DELETE, entity,
                String::class.java)
        return response.statusCode
    }

    fun getSetor(id: String): SetorData {
        val url = urlById(id)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<SetorData>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<SetorData>(url, HttpMethod.GET, entity,
                SetorData::class.java)
        return response.body
    }

    fun getSetor(codigo: String, nome: String): SetorData? {
        val setor = SetorData(codigo, nome)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<SetorData>(setor, headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<SetorData>(SETOR_URL, HttpMethod.PATCH, entity,
                SetorData::class.java)
        return response.body
    }
}