package br.com.eleanor.client.oracle

import br.com.eleanor.data.TecelagemData
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

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
                "  and pcpt_010.cd_pano_item = basi_010.item_estrutura "

        return jdbcTemplate.query(query) {
            rs, _ ->
            TecelagemData(
                    rs.getInt("ORDEM_TECELAGEM"), rs.getString("DESCRICAO"), rs.getDouble("QTDE_QUILOS_PROG"), rs.getString("PRODUTO")
            )
        }
    }
}