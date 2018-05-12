package br.com.eleanor.rules

import br.com.eleanor.model.MaquinaModel
import br.com.eleanor.model.TecelagemModel
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class IntegracaoSystextil(private val jdbcTemplate: NamedParameterJdbcTemplate) {
    fun listTecelagem(): List<TecelagemModel> {
        val query = "select pcpt_010.ordem_tecelagem, " +
            "pcpt_010.cd_pano_nivel99||'.'|| " +
            "pcpt_010.cd_pano_grupo||'.'||  " +
            "pcpt_010.cd_pano_subgrupo||'.'||  " +
            "pcpt_010.cd_pano_item||' - '|| " +
            "basi_010.narrativa as descricao, " +
            "pcpt_010.qtde_quilos_prog " +
            "from pcpt_010, basi_010 " +
            "where pcpt_010.cd_pano_nivel99 = basi_010.nivel_estrutura " +
            "  and pcpt_010.cd_pano_grupo = basi_010.grupo_estrutura " +
            "  and pcpt_010.cd_pano_subgrupo = basi_010.subgru_estrutura " +
            "  and pcpt_010.cd_pano_item = basi_010.item_estrutura "

        return jdbcTemplate.query(query) {
            rs, _ -> TecelagemModel(
                rs.getInt("ORDEM_TECELAGEM"), rs.getString("DESCRICAO"), rs.getDouble("QTDE_QUILOS_PROG")
            )
        }
    }

    fun getMaquina(codMaquinaEleanor: Int): MaquinaModel? {
        val sql = "select mqop_030.maq_sub_grupo_mq, " +
                "mqop_030.maq_sub_sbgr_maq, " +
                "mqop_030.numero_maquina " +
                "from mqop_030 where rownum = 1"
        // TO DO: Inserir parâmetros aqui
        return jdbcTemplate.query(sql) { rs, _ ->
            MaquinaModel(
                    rs.getString("MAQ_SUB_GRUPO_MQ"), rs.getString("MAQ_SUB_SBGR_MAQ"), rs.getInt("NUMERO_MAQUINA"))
        }.firstOrNull()
    }
}