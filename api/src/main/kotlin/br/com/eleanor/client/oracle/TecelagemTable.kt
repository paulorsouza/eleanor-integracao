package br.com.eleanor.client.oracle

import br.com.eleanor.data.MaquinaStData
import br.com.eleanor.data.TecelagemData
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.Date
import java.util.*
import kotlin.collections.HashMap

class TecelagemTable(private val jdbcTemplate: NamedParameterJdbcTemplate) {
    fun listTecelagem(): List<TecelagemData> {
        val query = "select pcpt_010.ordem_tecelagem, " +
                "pcpt_010.cd_pano_nivel99||'.'|| " +
                "pcpt_010.cd_pano_grupo||'.'||  " +
                "pcpt_010.cd_pano_subgrupo||'.'||  " +
                "pcpt_010.cd_pano_item||' - '|| " +
                "basi_010.narrativa as descricao, " +
                "pcpt_010.cd_pano_nivel99||'.'|| " +
                "pcpt_010.cd_pano_grupo||'.'||  " +
                "pcpt_010.cd_pano_subgrupo||'.'||  " +
                "pcpt_010.cd_pano_item as produto, " +
                "pcpt_010.qtde_quilos_prog " +
                "from pcpt_010, basi_010 " +
                "where pcpt_010.cd_pano_nivel99 = basi_010.nivel_estrutura " +
                "  and pcpt_010.cd_pano_grupo = basi_010.grupo_estrutura " +
                "  and pcpt_010.cd_pano_subgrupo = basi_010.subgru_estrutura " +
                "  and pcpt_010.cd_pano_item = basi_010.item_estrutura " +
                "  and pcpt_010.cd_pano_grupo = '00001' " +
                "  and pcpt_010.cd_pano_subgrupo = 'NCL' "

        return jdbcTemplate.query(query) {
            rs, _ ->
            TecelagemData(
                    rs.getInt("ORDEM_TECELAGEM"), rs.getString("DESCRICAO"), rs.getDouble("QTDE_QUILOS_PROG"), rs.getString("PRODUTO")
            )
        }
    }

    fun update(maquina: MaquinaStData, dataInicio: Calendar?, dataFim: Calendar?, codigo: String?) {
        val update = " update pcpt_010  " +
                "set pcpt_010.grupo_maquina = :maq_sub_grupo_mq, " +
                "    pcpt_010.subgru_maquina = :maq_sub_sbgr_maq, " +
                "    pcpt_010.numero_maquina = :numero_maquina, " +
                "    pcpt_010.data_prev_inicio = :data_inicio, " +
                "    pcpt_010.hora_prev_inicio = :hora_inicio, " +
                "    pcpt_010.data_prev_termino = :data_fim, " +
                "    pcpt_010.hora_prev_termino = :hora_fim, " +
                "from pcpt_010 " +
                "where pcpt_010.ordem_tecelagem = :ordem_tecelagem"

        val mapa = HashMap<String, Any?>()
        mapa["maq_sub_grupo_mq"] = maquina.maq_sub_grupo_mq
        mapa["maq_sub_sbgr_maq"] = maquina.maq_sub_sbgr_maq
        mapa["numero_maquina"] = maquina.numero_maquina
        mapa["data_inicio"] = dataInicio!!.toInstant()
        mapa["hora_inicio"] = dataInicio!!.toInstant()
        mapa["data_fim"] = dataFim!!.toInstant()
        mapa["hora_fim"] = dataFim!!.toInstant()
        mapa["ordem_tecelagem"] = codigo

        jdbcTemplate.update(update, mapa)
    }
}