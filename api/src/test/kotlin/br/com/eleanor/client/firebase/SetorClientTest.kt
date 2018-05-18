package br.com.eleanor.client.firebase

import org.junit.Test
import org.junit.Ignore

/* TODO configurar banco de testes para rodar os testes */
class SetorClientTest {
    @Ignore @Test
    fun listSetoresTest() {
        val client = SetorClient()
        val response = client.listSetores()
        assert(response!!.get("setor-1512772078265")!!.codigo.equals("1"))
        assert(response!!.get("setor-1512772078265")!!.nome.equals("confeccao"))
    }

    @Ignore @Test
    fun addSetorTest() {
        val client = SetorClient()
        val response = client.addSetor("TESTE.1", "TESTE 1")
        val key = response.name
        var responseList = client.listSetores()
        assert(responseList!!.get(key)!!.codigo.equals("TESTE.1"))
        assert(responseList!!.get(key)!!.nome.equals("TESTE 1"))
    }

    @Ignore @Test
    fun getSetorTest() {
        val client = SetorClient()
        val response = client.getSetor("setor-1512772078265")
        assert(response!!.codigo.equals("1"))
        assert(response!!.nome.equals("confeccao"))
    }
}