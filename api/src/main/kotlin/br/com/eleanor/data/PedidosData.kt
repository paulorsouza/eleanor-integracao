package br.com.eleanor.data

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.HashMap

class PedidosData {

    private val pedidos = HashMap<String, PedidoData>()

    @JsonAnyGetter
    fun getPedidos(): Map<String, PedidoData> {
        return pedidos
    }

    @JsonAnySetter
    fun setPedidos(name: String, value: PedidoData) {
        this.pedidos.put(name, value)
    }
}