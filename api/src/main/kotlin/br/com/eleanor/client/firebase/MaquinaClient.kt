package br.com.eleanor.client.firebase
import br.com.eleanor.data.MaquinaData
import br.com.eleanor.data.MaquinasData
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class MaquinaClient {
    val BASE_URL = "https://planeleanor.firebaseio.com/maquinas.json"

    fun getMaquinaUrl(): String{
        return "${BASE_URL}"
    }

    fun getMaquinas(): Map<String, MaquinaData>? {
        val maquinaUrl = getMaquinaUrl()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<MaquinaData>(headers)
        val restTemplate = RestTemplate()
        try{
            val response = restTemplate.exchange<MaquinasData>(maquinaUrl, HttpMethod.GET, entity, MaquinasData::class.java)
            val maquinasData = response.body
            println(maquinasData)
            return maquinasData.getMaquinas()
        } catch(ex: Exception){
            println(ex.message)
            return null
        }
    }
}