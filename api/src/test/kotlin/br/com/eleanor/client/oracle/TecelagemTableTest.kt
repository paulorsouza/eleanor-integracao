package br.com.eleanor.client.oracle

import br.com.eleanor.databases.HikariCustomConfig
import org.junit.Test
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class TecelagemTableTest {
    @Test
    fun listTecelagemTest() {
        val jdbcTemplate = NamedParameterJdbcTemplate(JdbcTemplate(HikariCustomConfig().getOracleTemplate()))
        val rule = TecelagemTable(jdbcTemplate)
        val list = rule.listTecelagem()
        println(list.size)
        println(list.first().descricao)
    }
}