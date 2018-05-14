package br.com.eleanor.client.oracle

import br.com.eleanor.data.MaquinaStData
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class MaquinaTable(private val jdbcTemplate: NamedParameterJdbcTemplate) {
    fun getMaquina(codMaquinaEleanor: String): MaquinaStData? {
        val sql = "select mqop_030.maq_sub_grupo_mq, " +
                "mqop_030.maq_sub_sbgr_maq, " +
                "mqop_030.numero_maquina " +
                "from mqop_030 " +
                "where mqop_030.maquina_integracao = :codigo "
        val mapa = HashMap<String, Any>()
        mapa["codigo"] = codMaquinaEleanor
        return jdbcTemplate.query(sql, mapa) { rs, _ ->
            MaquinaStData(
                    rs.getString("MAQ_SUB_GRUPO_MQ"), rs.getString("MAQ_SUB_SBGR_MAQ"), rs.getInt("NUMERO_MAQUINA"))
        }.firstOrNull()
    }
}