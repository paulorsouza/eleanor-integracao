package br.com.eleanor.rules

import br.com.eleanor.client.firebase.ProdutoClient
import br.com.eleanor.client.oracle.TecelagemTable
import br.com.eleanor.data.Status
import br.com.eleanor.databases.HikariCustomConfig
import org.junit.Ignore
import org.junit.Test
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class IntegracaoFirebaseTest {
    @Test
    fun integracaoTest() {
        val jdbcTemplate = NamedParameterJdbcTemplate(JdbcTemplate(HikariCustomConfig().getOracleTemplate()))
        val integracao = IntegracaoFirebase(jdbcTemplate)
        val result = integracao.integrarPedidos()
        result.forEach {
            r -> println(r.msg)
        }
    }

    @Test
    fun getProdutoKeyTest() {
        val jdbcTemplate = NamedParameterJdbcTemplate(JdbcTemplate(HikariCustomConfig().getOracleTemplate()))
        val produtos = ProdutoClient().listProdutos()
        val integracao = IntegracaoFirebase(jdbcTemplate)
        val key = integracao.getProdutoKey(produtos!!, "1.JUCCA.P.000001")
        assert("produto-1512826961484".equals(key))
        val keyNull = integracao.getProdutoKey(produtos!!, "error")
        assert(keyNull.isNullOrEmpty())
    }
}
