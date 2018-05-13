package br.com.eleanor.client.oracle

import br.com.eleanor.data.MaquinaStData
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class MaquinaTable(private val jdbcTemplate: NamedParameterJdbcTemplate) {
    fun getMaquina(codMaquinaEleanor: Int): MaquinaStData? {
        val sql = "select mqop_030.maq_sub_grupo_mq, " +
                "mqop_030.maq_sub_sbgr_maq, " +
                "mqop_030.numero_maquina " +
                "from mqop_030 where rownum = 1"
        // TO DO: Inserir parâmetros aqui
        return jdbcTemplate.query(sql) { rs, _ ->
            MaquinaStData(
                    rs.getString("MAQ_SUB_GRUPO_MQ"), rs.getString("MAQ_SUB_SBGR_MAQ"), rs.getInt("NUMERO_MAQUINA"))
        }.firstOrNull()
    }
}