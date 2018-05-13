package br.com.eleanor.client.firebase

import org.junit.Test

class MaquinaClientTest {

    @Test
    fun listMaquinasTest() {
        val client = MaquinaClient()
        val response = client.listMaquinas()
        assert(response!!.get("-LCM5PtBJ9iUkLxnxze4")!!.codigo.equals("1"))
        assert(response!!.get("-LCM5PtBJ9iUkLxnxze4")!!.nome.equals("teste"))
    }

    @Test
    fun addMaquinaTest() {
        val client = MaquinaClient()
        val response = client.addMaquina("TESTE.1", "TESTE 1")
        val key = response.name
        var responseList = client.listMaquinas()
        assert(responseList!!.get(key)!!.codigo.equals("TESTE.1"))
        assert(responseList!!.get(key)!!.nome.equals("TESTE 1"))
        // Sem permissão para deletar
//        val status = client.deleteMaquina(key)
//        println(status)
//        responseList = client.listMaquinas()
//        assert(responseList!!.get(key)!!.codigo.equals("TESTE.1"))
    }

    @Test
    fun getMaquinaTest() {
        val client = MaquinaClient()
        val response = client.getMaquina("-LCM5PtBJ9iUkLxnxze4")
        println(response)
//        assert(response!!.codigo.equals("1"))
//        assert(response!!.nome.equals("teste"))
    }
}