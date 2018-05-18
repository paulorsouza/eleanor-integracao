package br.com.eleanor.rules

import br.com.eleanor.client.oracle.MaquinaTable
import br.com.eleanor.client.oracle.TecelagemTable
import br.com.eleanor.data.PedidoData
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.Calendar

class IntegracaoOracle(private val jdbcTemplate: NamedParameterJdbcTemplate) {
    fun atualizarPedido(codigoMaquina: String, pedido: PedidoData) {
        try {
            val maquina = MaquinaTable(jdbcTemplate).getMaquina(codigoMaquina)
            var dataInicio = Calendar.getInstance()
            dataInicio.timeInMillis = pedido.start_time!!.toLong()
            var dataFim = Calendar.getInstance()
            dataFim.timeInMillis = pedido.end_time!!.toLong()
            val tecelagemTable = TecelagemTable(jdbcTemplate)
            tecelagemTable.update(maquina!!, dataInicio, dataFim, pedido.codigo!!.toInt())

        } catch (ex: Exception) {
            println(ex)
        }
    }
}

