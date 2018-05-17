package br.com.eleanor.rules

import br.com.eleanor.client.firebase.PedidoClient
import br.com.eleanor.client.firebase.ProdutoClient
import br.com.eleanor.data.*

class IntegracaoFirebase {

    val SUCCESS_MSG = "Pedido inserido no firebase com sucesso"

    fun getProdutoKey(produtos: Map<String, ProdutoData>, codigo: String): String? {
        val produtoKey = produtos.filter {
            map -> map.value.codigo == codigo
        }.keys.firstOrNull()

        return produtoKey
    }

    fun parseCodigoProduto(nivel: String, grupo: String, sub: String, item: String): String {
        return "${nivel}.${grupo}.${sub}.${item}"
    }

    fun integrarPedidos(pedidosOracle: List<TecelagemData>): ArrayList<IntegracaoResultData> {
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
                return IntegracaoResultData(Status.ERROR, "Produto não cadastrado no eleanor", null, tecelagem.produto!!)
            }
            val pedidoFirebase = pedidos?.values?.firstOrNull {
                v -> v.codigo?.toInt() == tecelagem.ordem_tecelagem }
            if (pedidoFirebase != null) {
                return IntegracaoResultData(Status.ERROR, "Pedido já integrado", pedidoFirebase.id, tecelagem.ordem_tecelagem.toString())
            }
            val key = client.addPedido(tecelagem.ordem_tecelagem.toString(), tecelagem.produto, tecelagem.qtde_quilos_prog!!.toInt(), tecelagem.descricao)
            return IntegracaoResultData(Status.SUCCESS, SUCCESS_MSG, key.name, tecelagem.ordem_tecelagem.toString())
        } catch (ex: Exception) {
            return IntegracaoResultData(Status.ERROR, ex.message, null, tecelagem.ordem_tecelagem.toString())
        }
    }

    /*TODO testes - apagar*/
    fun integrarPedido(tecelagem: TecelagemData): IntegracaoResultData {
        val produtos = ProdutoClient().listProdutos()
        val produtoKey = getProdutoKey(produtos.orEmpty(), tecelagem.produto.toString())
        val client = PedidoClient()
        val pedidos = client.listPedidos()
        val pedidoFirebase = pedidos?.values?.firstOrNull { v -> v.codigo?.toInt() == tecelagem.ordem_tecelagem }
        println(pedidoFirebase)
        if (pedidoFirebase != null) {
            return (IntegracaoResultData(Status.ERROR, "Pedido já integrado", pedidoFirebase.id, tecelagem.ordem_tecelagem.toString()))
        }
        val key = client.addPedido(tecelagem.ordem_tecelagem.toString(), produtoKey,
                tecelagem.qtde_quilos_prog!!.toInt(), tecelagem.descricao)
        return IntegracaoResultData(Status.SUCCESS, SUCCESS_MSG, key.name, tecelagem.ordem_tecelagem.toString())
    }
}