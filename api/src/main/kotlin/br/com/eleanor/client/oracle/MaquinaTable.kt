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

    fun listMaquinas(): List<Pair<String, String>>? {
        val sql = "select c.maq_sub_grupo_mq||'.'|| " +
                "c.maq_sub_sbgr_maq||'.'|| " +
                "c.numero_maquina codigo, " +
                "a.nome_grupo_maq||'.'|| " +
                "b.nome_sbgrupo_maq||'.'|| " +
                "c.nome_maquina nome, " +
                " 4 setor " +
                "from mqop_010 a, mqop_020 b, mqop_030 c " +
                "where 1 = 1 " +
                "  and a.grupo_maquina = b.grupo_maquina " +
                "  and b.grupo_maquina = c.maq_sub_grupo_mq " +
                "  and b.subgrupo_maquina = c.maq_sub_sbgr_maq " +
                "  and b.grupo_maquina = 'TEAR' " +
                "order by c.maq_sub_grupo_mq, " +
                "c.maq_sub_sbgr_maq, " +
                "c.numero_maquina "
        return jdbcTemplate.query(sql) {
            rs, _ -> Pair<String, String>(rs.getString("codigo"), rs.getString("nome"))
        }
    }
}