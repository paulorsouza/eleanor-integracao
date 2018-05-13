package br.com.eleanor.client.firebase

import br.com.eleanor.data.*
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class PedidoClient {
    val BASE_URL = "https://planeleanor.firebaseio.com/pedidos"
    val PEDIDO_URL = "${BASE_URL}.json"

    fun urlById(id: String): String {
        return "${BASE_URL}/${id}.json"
    }

    fun listPedidos(): Map<String, PedidoData>? {
        val pedidoUrl = PEDIDO_URL
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<PedidoData>(headers)
        val restTemplate = RestTemplate()
        try{
            val response = restTemplate.exchange<PedidosData>(pedidoUrl, HttpMethod.GET, entity, PedidosData::class.java)
            val pedidosData = response.body
            return pedidosData.getPedidos()
        } catch(ex: Exception){
            println(ex.message)
            return null
        }
    }

    fun addPedido(canChangeGroup: Boolean?, canMove: Boolean?, canResize: String?, className: String?,
                  codigo : String?, end_time: Float?, group: String?, id: String?, planejado: Boolean?,
                  produto: String?, produtoDict: ProdutoData?, quantidade: Integer?, sizeTime: Integer?, start_time: Float?, title: String?): FirebaseKeyData {
        val pedido = PedidoData(canChangeGroup, canMove, canResize, className,
                codigo, end_time, group, id, planejado,
                produto, produtoDict, quantidade, sizeTime, start_time, title)
        val headers = HttpHeaders()
        headers.accept = arrayListOf(MediaType.APPLICATION_JSON)
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<PedidoData>(pedido, headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<FirebaseKeyData>(PEDIDO_URL,
                HttpMethod.POST, entity, FirebaseKeyData::class.java)
        return response.body
    }

    fun deletePedido(id: String): HttpStatus? {
        val url = urlById(id)
        val headers = HttpHeaders()
        val entity = HttpEntity<String>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<String>(url, HttpMethod.DELETE, entity,
                String::class.java)
        return response.statusCode
    }

    fun getPedido(id: String): PedidoData {
        val url = urlById(id)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<PedidoData>(headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<PedidoData>(url, HttpMethod.GET, entity,
                PedidoData::class.java)
        return response.body
    }

    fun getPedido(canChangeGroup: Boolean?, canMove: Boolean?, canResize: String?, className: String?,
                  codigo : String?, end_time: Float?, group: String?, id: String?, planejado: Boolean?,
                  produto: String?, produtoDict: ProdutoData?, quantidade: Integer?, sizeTime: Integer?, start_time: Float?, title: String?): PedidoData? {
        val pedido = PedidoData(canChangeGroup, canMove, canResize, className,
                codigo, end_time, group, id, planejado,
                produto, produtoDict, quantidade, sizeTime, start_time, title)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<PedidoData>(pedido, headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<PedidoData>(PEDIDO_URL, HttpMethod.PATCH, entity,
                PedidoData::class.java)
        return response.body
    }
}