package br.com.eleanor.client.firebase

import org.junit.Test

class MaquinaClientTest {

    @Test
    fun getMaquinasTest() {
        val client = MaquinaClient()
        val response = client.getMaquinas()
        assert(response!!.get("-LCM5PtBJ9iUkLxnxze4")!!.codigo.equals("1"))
        assert(response!!.get("-LCM5PtBJ9iUkLxnxze4")!!.nome.equals("teste"))
    }
}