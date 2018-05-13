package br.com.eleanor.client.firebase
import br.com.eleanor.data.MaquinaData
import br.com.eleanor.data.MaquinasData
import br.com.eleanor.data.FirebaseKeyData
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class MaquinaClient {
    val MAQUINA_URL = "https://planeleanor.firebaseio.com/maquinas.json"

    fun urlById(id: String): String {
        return "${MAQUINA_URL}/${id}"
    }

    fun listMaquinas(): Map<String, MaquinaData>? {
        val maquinaUrl = MAQUINA_URL
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<MaquinaData>(headers)
        val restTemplate = RestTemplate()
        try{
            val response = restTemplate.exchange<MaquinasData>(maquinaUrl, HttpMethod.GET, entity, MaquinasData::class.java)
            val maquinasData = response.body
            return maquinasData.getMaquinas()
        } catch(ex: Exception){
            println(ex.message)
            return null
        }
    }

    fun addMaquina(codigo: String, nome: String): FirebaseKeyData {
        val maquina = MaquinaData(codigo, nome)
        val headers = HttpHeaders()
        headers.accept = arrayListOf(MediaType.APPLICATION_JSON)
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<MaquinaData>(maquina, headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<FirebaseKeyData>(MAQUINA_URL,
                HttpMethod.POST, entity, FirebaseKeyData::class.java)
        return response.body
    }

    fun deleteMaquina(id: String): HttpStatus? {
        val url = urlById(id)
        val headers = HttpHeaders()
        val entity = HttpEntity<String>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<String>(url, HttpMethod.DELETE, entity,
                String::class.java)
        return response.statusCode
    }
}