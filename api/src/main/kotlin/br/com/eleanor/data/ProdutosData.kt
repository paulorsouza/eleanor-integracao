package br.com.eleanor.data

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.*

class ProdutosData {

    private val produtos = HashMap<String, ProdutoData>()

    @JsonAnyGetter
    fun getProdutos(): Map<String, ProdutoData> {
        return produtos
    }

    @JsonAnySetter
    fun setProdutos(name: String, value: ProdutoData) {
        this.produtos.put(name, value)
    }
}