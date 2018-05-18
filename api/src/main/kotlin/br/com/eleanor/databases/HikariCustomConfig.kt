package br.com.eleanor.databases

import com.zaxxer.hikari.HikariDataSource

class HikariCustomConfig {
    private val oracleTemplate: HikariDataSource

    init {
        oracleTemplate = oracleTemplate()
    }

    fun getOracleTemplate(): HikariDataSource = oracleTemplate

    private fun oracleTemplate(): HikariDataSource {
        val hikariDS = HikariDataSource()
        hikariDS.setDriverClassName("oracle.jdbc.driver.OracleDriver")
        hikariDS.connectionTimeout = 5000
        hikariDS.maximumPoolSize = 5
        hikariDS.password = "oracle"
        hikariDS.username = "systextil"
//        hikariDS.jdbcUrl = "jdbc:oracle:thin:@192.168.0.70:1521:teste"
        hikariDS.jdbcUrl = "jdbc:oracle:thin:@177.135.121.210:46759:XE"
        return hikariDS
    }
}
