package br.com.eleanor.data

import com.fasterxml.jackson.annotation.JsonAnyGetter
import java.util.HashMap
import com.fasterxml.jackson.annotation.JsonAnySetter

class MaquinasData{

    private val maquinas = HashMap<String, MaquinaData>()

    @JsonAnyGetter
    fun getMaquinas(): Map<String, MaquinaData> {
        return maquinas
    }

    @JsonAnySetter
    fun setAddress(name: String, value: MaquinaData) {
        this.maquinas.put(name, value)
    }
}