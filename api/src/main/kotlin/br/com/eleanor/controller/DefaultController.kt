package br.com.eleanor.controller

import br.com.eleanor.databases.HikariCustomConfig
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class DefaultController {
    protected val oracleTemplate: NamedParameterJdbcTemplate = NamedParameterJdbcTemplate(HikariCustomConfig().getOracleTemplate())
    protected val oracleTemplateNonNamed: JdbcTemplate = JdbcTemplate(HikariCustomConfig().getOracleTemplate())
}