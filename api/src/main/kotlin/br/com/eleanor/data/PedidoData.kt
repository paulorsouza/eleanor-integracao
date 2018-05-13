package br.com.eleanor.data

data class PedidoData(val canChangeGroup: Boolean?, val canMove: Boolean?, val canResize: String?, val className: String?,
                      val codigo : String?, val end_time: Float?, val group: String?, val id: String?, val planejado: Boolean?,
                      val produto: String?, val produtoDict: ProdutoData?, val quantidade: Int?, val sizeTime: Int?, val start_time: Float?, val title: String?)