package br.com.eleanor.client.firebase

import org.junit.Test

class ProdutoClientTest {

    @Test
    fun listProdutosTest() {
        val client = ProdutoClient()
        val response = client.listProdutos()
        assert(response!!.get("produto-1512826961484")!!.codigo.equals("1.JUCCA.P.000001"))
        assert(response!!.get("produto-1512826961484")!!.nome.equals("Camiseta Junior"))
        assert(response!!.get("produto-1512826961484")!!.maquinasRelacionadas!!.getMaquinaRelacionada().get("maquina-relacionada-1512826958873")!!.maquina.equals("maquina-1512826275709"))
    }

    @Test
    fun addProdutoTest() {
        val client = ProdutoClient()
        val response = client.addProduto("TESTE.1", null,"TESTE 1")
        val key = response.name
        var responseList = client.listProdutos()
        assert(responseList!!.get(key)!!.codigo.equals("TESTE.1"))
        assert(responseList!!.get(key)!!.nome.equals("TESTE 1"))
    }

    @Test
    fun getProdutoTest() {
        val client = ProdutoClient()
        val response = client.getProduto("produto-1512826961484")
        println(response)
        assert(response!!.codigo.equals("1.JUCCA.P.000001"))
        assert(response!!.nome.equals("Camiseta Junior"))
        assert(response!!.maquinasRelacionadas!!.getMaquinaRelacionada().get("maquina-relacionada-1512826958873")!!.maquina.equals("maquina-1512826275709"))
    }
}