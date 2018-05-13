package br.com.eleanor.data

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.HashMap

class MaquinasRelacionadasData {

    private val maquinaRelacionada = HashMap<String, MaquinaRelacionadaData>()

    @JsonAnyGetter
    fun getMaquinaRelacionada(): Map<String, MaquinaRelacionadaData> {
        return maquinaRelacionada
    }

    @JsonAnySetter
    fun setMaquinaRelacionada(name: String, value: MaquinaRelacionadaData) {
        this.maquinaRelacionada.put(name, value)
    }
}