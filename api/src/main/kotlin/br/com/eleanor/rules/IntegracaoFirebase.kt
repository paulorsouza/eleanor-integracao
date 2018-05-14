package br.com.eleanor.rules

import br.com.eleanor.client.firebase.PedidoClient
import br.com.eleanor.data.IntegracaoResultData
import br.com.eleanor.data.Status
import br.com.eleanor.data.TecelagemData

class IntegracaoFirebase {

    val SUCCESS_MSG = "Pedido inserido no firebase com sucesso"

    fun integrarPedidos(pedidos: List<TecelagemData>): ArrayList<IntegracaoResultData> {
        val result = arrayListOf<IntegracaoResultData>()
        val client = PedidoClient()
        pedidos.forEach {
            pedido ->
            try {
                val key = client.addPedido(pedido.ordem_tecelagem.toString(), pedido.produto, pedido.qtde_quilos_prog!!.toInt(), pedido.descricao)
                result.add(IntegracaoResultData(Status.SUCCESS, SUCCESS_MSG, key.name, pedido.ordem_tecelagem.toString()))
            } catch (ex: Exception) {
                result.add(IntegracaoResultData(Status.ERROR, ex.message, null, pedido.ordem_tecelagem.toString()))
            }
        }
        return result
    }
}