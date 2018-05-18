package br.com.eleanor.client.oracle

import br.com.eleanor.client.firebase.PedidoClient
import br.com.eleanor.databases.HikariCustomConfig
import org.junit.Test
import org.junit.Ignore
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.Timestamp
import java.util.*

class TecelagemTableTest {
    @Ignore @Test
    fun listTecelagemTest() {
        val jdbcTemplate = NamedParameterJdbcTemplate(JdbcTemplate(HikariCustomConfig().getOracleTemplate()))
        val rule = TecelagemTable(jdbcTemplate)
        val list = rule.listTecelagem()
        println(list.size)
        println(list.first().descricao)
    }

    @Test
    fun updateTecelagem() {
        val jdbcTemplate = NamedParameterJdbcTemplate(JdbcTemplate(HikariCustomConfig().getOracleTemplate()))
        val maq = MaquinaTable(jdbcTemplate).getMaquina("TING.003")
        val client = PedidoClient()
        val pedido = client.getPedido("-LCWFFkkjhzLtDYgMiBY")
        val res = TecelagemTable(jdbcTemplate).update(maq!!, pedido)
    }
}