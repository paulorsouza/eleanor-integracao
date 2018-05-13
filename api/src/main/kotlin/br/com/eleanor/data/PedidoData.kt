package br.com.eleanor.data

import java.sql.Date

data class PedidoData(val canChangeGroup: Boolean, val canMoove: Boolean, val canResize: String, val className: String,
                      val codigo : String, val end_time: Date, val group: String, val id: String)