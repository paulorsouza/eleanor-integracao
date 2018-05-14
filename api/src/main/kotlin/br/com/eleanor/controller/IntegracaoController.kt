package br.com.eleanor.controller

import br.com.eleanor.client.oracle.TecelagemTable
import br.com.eleanor.data.IntegracaoResultData
import br.com.eleanor.rules.IntegracaoFirebase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/integracao")
class IntegracaoController : DefaultController() {
    @GetMapping("/firebase/pedidos")
    fun integrarPedido() : List<IntegracaoResultData> {
        val rule = TecelagemTable(oracleTemplate)
        val list = rule.listTecelagem()
        val integracao = IntegracaoFirebase()
        return integracao.integrarPedidos(list)
    }
}