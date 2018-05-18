package br.com.eleanor.client.firebase

import org.junit.Ignore
import org.junit.Test

/* TODO configurar banco de testes para rodar os testes */
class PedidoClientTest {

    @Ignore @Test
    fun listPedidosTest() {
        val client = PedidoClient()
        val response = client.listPedidos()
        assert(response!!.get("pedido-1520180800111")!!.codigo.equals("1"))
        assert(response!!.get("pedido-1520180800111")!!.group.equals("TING.001"))
    }

    @Ignore @Test
    fun addPedidoTest() {
        val client = PedidoClient()
        val response = client.addPedido(null,null,null,null,"TESTE-1234",null, "TESTE-1234",null,null,null,null,null,null,null,null)
        val key = response.name
        var responseList = client.listPedidos()
        assert(responseList!!.get(key)!!.codigo.equals("TESTE-1234"))
        assert(responseList!!.get(key)!!.group.equals("TESTE-1234"))
    }

    @Ignore @Test
    fun addPedidoWithDefaultsTest() {
        val client = PedidoClient()
        val response = client.addPedido("teste1", "produto", 1, "produto 1")
        val key = response.name
        var responseList = client.listPedidos()
        assert(responseList!!.get(key)!!.codigo.equals("teste1"))
        assert(responseList!!.get(key)!!.group.equals(""))
    }

    @Ignore @Test
    fun getPedidoTest() {
        val client = PedidoClient()
        val response = client.getPedido("pedido-1520180800111")
        println(response)
        assert(response!!.codigo.equals("1"))
        assert(response!!.group.equals("TING.001"))
    }

    @Ignore @Test
    fun cleanPedidos() {
        val client = PedidoClient()
        val response = client.listPedidos()
        val pedidos = response!!.filter { r -> !r.key.startsWith("pedido-") }
        pedidos.forEach { p -> client.deletePedido(p.key) }
    }

}