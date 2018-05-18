package br.com.eleanor.rules

import br.com.eleanor.client.firebase.PedidoClient
import br.com.eleanor.client.firebase.ProdutoClient
import br.com.eleanor.client.oracle.MaquinaTable
import br.com.eleanor.client.oracle.TecelagemTable
import br.com.eleanor.data.*
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.Calendar

class IntegracaoOracle(private val jdbcTemplate: NamedParameterJdbcTemplate) {

    val maquinaTable = MaquinaTable(jdbcTemplate)
    val tecelagemTable = TecelagemTable(jdbcTemplate)
    val pedidoClient = PedidoClient()
    val SUCCESS_MSG = "Pedido atualizado com sucesso"
    val MAQUINA_MSG = "Maquina do pedido não vinculada no systextil"

    fun atualizarPedidos(): ArrayList<IntegracaoResultData> {
        val result = arrayListOf<IntegracaoResultData>()
        val pedidos = pedidoClient.listPedidos()
        pedidos?.values?.forEach { pedido ->
            result.add(atualizarPedido(pedido))
        }
        return result
    }

    fun atualizarPedido(pedido: PedidoData): IntegracaoResultData {
        try {
            val maquina = maquinaTable.getMaquina(pedido.group!!)
            if (maquina == null) {
                return IntegracaoResultData(Status.ERROR, MAQUINA_MSG, pedido.id, pedido.codigo)
            }
            tecelagemTable.update(maquina!!, pedido)
            return IntegracaoResultData(Status.SUCCESS, SUCCESS_MSG, pedido.id, pedido.codigo)
        }
        catch (ex: Exception) {
            return IntegracaoResultData(Status.ERROR, ex.message, pedido.id, pedido.codigo)
        }
    }

}

