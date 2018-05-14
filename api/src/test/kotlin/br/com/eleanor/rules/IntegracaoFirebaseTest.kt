package br.com.eleanor.rules

import br.com.eleanor.client.oracle.TecelagemTable
import br.com.eleanor.data.Status
import br.com.eleanor.databases.HikariCustomConfig
import org.junit.Test
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class IntegracaoFirebaseTest {
    @Test
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
}
