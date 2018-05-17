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
    @Ignore @Test
    fun integracaoTest() {
        val jdbcTemplate = NamedParameterJdbcTemplate(JdbcTemplate(HikariCustomConfig().getOracleTemplate()))
        val rule = TecelagemTable(jdbcTemplate)
        val list = rule.listTecelagem()
        val integracao = IntegracaoFirebase()
        val result = integracao.integrarPedidos(list)
        result.forEach {
            r -> assert(r.status == Status.SUCCESS)
        }
    }

    @Test
    fun integrarPedidoTest() {
        val jdbcTemplate = NamedParameterJdbcTemplate(JdbcTemplate(HikariCustomConfig().getOracleTemplate()))
        val rule = TecelagemTable(jdbcTemplate)
        val list = rule.listTecelagem()
        val tecelagem = list.first()
        val integracao = IntegracaoFirebase()
        integracao.integrarPedido(tecelagem)
    }

    @Test
    fun getProdutoKeyTest() {
        val produtos = ProdutoClient().listProdutos()
        val integracao = IntegracaoFirebase()
        val key = integracao.getProdutoKey(produtos!!, "1.JUCCA.P.000001")
        assert("produto-1512826961484".equals(key))
        val keyNull = integracao.getProdutoKey(produtos!!, "error")
        assert(keyNull.isNullOrEmpty())
    }
}
