package br.com.eleanor.controller

import br.com.eleanor.data.IntegracaoResultData
import br.com.eleanor.databases.HikariCustomConfig
import br.com.eleanor.rules.IntegracaoFirebase
import br.com.eleanor.rules.IntegracaoOracle
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/integracao")
class IntegracaoController {
    val oracleTemplate = NamedParameterJdbcTemplate(HikariCustomConfig().getOracleTemplate())

    @GetMapping("/firebase/pedidos/integrar", produces = arrayOf("application/json"))
    fun integrarPedidos() : List<IntegracaoResultData> {
        val integracao = IntegracaoFirebase(oracleTemplate)
        return integracao.integrarPedidos()
    }

    @GetMapping("/oracle/pedidos/atualizar", produces = arrayOf("application/json"))
    fun atualizarPedidos() : List<IntegracaoResultData> {
        val integracao = IntegracaoOracle(oracleTemplate)
        return integracao.atualizarPedidos()
    }

    @GetMapping("/oracle/maquinas/integrar", produces = arrayOf("application/json"))
    fun atualizarMaquinas() : List<IntegracaoResultData> {
        val integracao = IntegracaoFirebase(oracleTemplate)
        return integracao.integrarMaquinas()
    }
}