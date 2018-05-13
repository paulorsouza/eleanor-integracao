package br.com.eleanor.client.firebase

import org.junit.Test

class PedidoClientTest {

    @Test
    fun listPedidosTest() {
        val client = PedidoClient()
        val response = client.listPedidos()
        assert(response!!.get("pedido-1520180800111")!!.codigo.equals("1"))
        assert(response!!.get("pedido-1520180800111")!!.group.equals("TING.001"))
    }

    @Test
    fun addPedidoTest() {
        val client = PedidoClient()
        val response = client.addPedido(null,null,null,null,"TESTE-1234",null, "TESTE-1234",null,null,null,null,null,null,null,null)
        val key = response.name
        var responseList = client.listPedidos()
        assert(responseList!!.get(key)!!.codigo.equals("TESTE-1234"))
        assert(responseList!!.get(key)!!.group.equals("TESTE-1234"))
    }

    @Test
    fun getPedidoTest() {
        val client = PedidoClient()
        val response = client.getPedido("pedido-1520180800111")
        println(response)
        assert(response!!.codigo.equals("1"))
        assert(response!!.group.equals("TING.001"))
    }
}