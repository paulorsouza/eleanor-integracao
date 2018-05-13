package br.com.eleanor.client.firebase

import br.com.eleanor.data.FirebaseKeyData
import br.com.eleanor.data.MaquinasRelacionadasData
import br.com.eleanor.data.ProdutoData
import br.com.eleanor.data.ProdutosData
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class ProdutoClient {
    val BASE_URL = "https://planeleanor.firebaseio.com/produtos"
    val PRODUTO_URL = "${BASE_URL}.json"

    fun urlById(id: String): String {
        return "${BASE_URL}/${id}.json"
    }

    fun listProdutos(): Map<String, ProdutoData>? {
        val produtoUrl = PRODUTO_URL
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<ProdutoData>(headers)
        val restTemplate = RestTemplate()
        try{
            val response = restTemplate.exchange<ProdutosData>(produtoUrl, HttpMethod.GET, entity, ProdutosData::class.java)
            val produtosData = response.body
            return produtosData.getProdutos()
        } catch(ex: Exception){
            println(ex.message)
            return null
        }
    }

    fun addProduto(codigo: String, maquinasRelacionadas: MaquinasRelacionadasData?, nome: String): FirebaseKeyData {
        val produto = ProdutoData(codigo, maquinasRelacionadas, nome)
        val headers = HttpHeaders()
        headers.accept = arrayListOf(MediaType.APPLICATION_JSON)
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<ProdutoData>(produto, headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<FirebaseKeyData>(PRODUTO_URL,
                HttpMethod.POST, entity, FirebaseKeyData::class.java)
        return response.body
    }

    fun deleteProduto(id: String): HttpStatus? {
        val url = urlById(id)
        val headers = HttpHeaders()
        val entity = HttpEntity<String>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<String>(url, HttpMethod.DELETE, entity,
                String::class.java)
        return response.statusCode
    }

    fun getProduto(id: String): ProdutoData {
        val url = urlById(id)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<ProdutoData>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<ProdutoData>(url, HttpMethod.GET, entity,
                ProdutoData::class.java)
        return response.body
    }

    fun getProduto(codigo: String, maquinasRelacionadas: MaquinasRelacionadasData?,nome: String): ProdutoData? {
        val produto = ProdutoData(codigo, maquinasRelacionadas, nome)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<ProdutoData>(produto, headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<ProdutoData>(PRODUTO_URL, HttpMethod.PATCH, entity,
                ProdutoData::class.java)
        return response.body
    }
}