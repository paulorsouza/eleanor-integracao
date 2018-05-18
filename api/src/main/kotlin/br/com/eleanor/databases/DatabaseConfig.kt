package br.com.eleanor.databases

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    @Bean(name = arrayOf("oracleSource"))
    @Primary
    @ConfigurationProperties(prefix = "spring.oracle")
    fun oracleSource(): DataSource = DataSourceBuilder.create().build()

    @Bean(name = arrayOf("oracleTemplate"))
    @Primary
    @Autowired
    fun oracleTemplate(@Qualifier("oracleSource") source: DataSource): NamedParameterJdbcTemplate = NamedParameterJdbcTemplate(source)
}