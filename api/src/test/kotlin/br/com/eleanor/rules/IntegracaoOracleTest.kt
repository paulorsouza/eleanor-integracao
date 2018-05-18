package br.com.eleanor.rules

import br.com.eleanor.databases.HikariCustomConfig
import org.junit.Ignore
import org.junit.Test
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

/* TODO configurar banco de testes para rodar os testes */
class IntegracaoOracleTest {
    @Ignore @Test
    fun integracaoTest() {
        val jdbcTemplate = NamedParameterJdbcTemplate(JdbcTemplate(HikariCustomConfig().getOracleTemplate()))
        val integracao = IntegracaoOracle(jdbcTemplate)
        val result = integracao.atualizarPedidos()
        result.forEach {
            r -> println("${r.msg} - ${r.idOracle}")
        }
    }
}