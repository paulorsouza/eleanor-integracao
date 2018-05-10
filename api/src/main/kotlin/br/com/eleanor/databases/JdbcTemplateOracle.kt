package br.com.eleanor.databases

import org.springframework.jdbc.core.JdbcTemplate

class JdbcTemplateOracle : JdbcTemplate()
class JdbcTemplateMysql: JdbcTemplate(HikariCustomConfig().getMysqlTemplate())