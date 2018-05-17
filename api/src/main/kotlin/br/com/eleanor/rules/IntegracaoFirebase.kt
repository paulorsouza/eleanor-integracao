package br.com.eleanor.rules

import br.com.eleanor.client.firebase.PedidoClient
import br.com.eleanor.client.firebase.ProdutoClient
import br.com.eleanor.client.oracle.TecelagemTable
import br.com.eleanor.data.*
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class IntegracaoFirebase(private val jdbcTemplate: NamedParameterJdbcTemplate) {

    val SUCCESS_MSG = "Pedido inserido no firebase com sucesso"
    val PRODUTO_MSG = "Produto não cadastrado no eleanor"
    val PEDIDO_MSG = "Pedido já integrado"

    fun getProdutoKey(produtos: Map<String, ProdutoData>, codigo: String): String? {
        val produtoKey = produtos.filter {
            map -> map.value.codigo == codigo
        }.keys.firstOrNull()
        return produtoKey
    }

    fun integrarPedidos(): ArrayList<IntegracaoResultData> {
        val rule = TecelagemTable(jdbcTemplate)
        val pedidosOracle = rule.listTecelagem()
        val result = arrayListOf<IntegracaoResultData>()
        val client = PedidoClient()
        val produtosFirebase = ProdutoClient().listProdutos()
        val pedidosFirebase = client.listPedidos()
        pedidosOracle.forEach {
            pedido -> result.add(integrarPedido(pedido, client, produtosFirebase, pedidosFirebase))
        }
        return result
    }

    fun integrarPedido(tecelagem: TecelagemData, client: PedidoClient, produtos: Map<String, ProdutoData>?,
                       pedidos: Map<String, PedidoData>?): IntegracaoResultData {
        try {
            val produtoKey = getProdutoKey(produtos!!, tecelagem.produto!!)
            if (produtoKey.isNullOrEmpty()) {
                return IntegracaoResultData(Status.ERROR, PRODUTO_MSG, null, tecelagem.produto!!)
            }
            val pedidoFirebase = pedidos?.values?.firstOrNull {
                v -> v.codigo?.toInt() == tecelagem.ordem_tecelagem }
            if (pedidoFirebase != null) {
                return IntegracaoResultData(Status.ERROR, PEDIDO_MSG, pedidoFirebase.id, tecelagem.ordem_tecelagem.toString())
            }
            val key = client.addPedido(tecelagem.ordem_tecelagem.toString(), tecelagem.produto, tecelagem.qtde_quilos_prog!!.toInt(), tecelagem.descricao)
            return IntegracaoResultData(Status.SUCCESS, SUCCESS_MSG, key.name, tecelagem.ordem_tecelagem.toString())
        } catch (ex: Exception) {
            return IntegracaoResultData(Status.ERROR, ex.message, null, tecelagem.ordem_tecelagem.toString())
        }
    }
}