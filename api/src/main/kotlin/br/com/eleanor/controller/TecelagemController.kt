package br.com.eleanor.controller

import br.com.eleanor.client.oracle.TecelagemTable
import br.com.eleanor.data.TecelagemData
import br.com.eleanor.databases.HikariCustomConfig
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tecelagem")
class TecelagemController {
    val oracleTemplate = NamedParameterJdbcTemplate(HikariCustomConfig().getOracleTemplate())
    @GetMapping("/list")
    fun integrarPedido() : List<TecelagemData> {
        val rule = TecelagemTable(oracleTemplate)
        return rule.listTecelagem()
    }
}