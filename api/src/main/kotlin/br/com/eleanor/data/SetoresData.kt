package br.com.eleanor.data

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.HashMap

class SetoresData {

    private val setores = HashMap<String, SetorData>()

    @JsonAnyGetter
    fun getSetores(): Map<String, SetorData> {
        return setores
    }

    @JsonAnySetter
    fun setSetores(name: String, value: SetorData) {
        this.setores.put(name, value)
    }
}