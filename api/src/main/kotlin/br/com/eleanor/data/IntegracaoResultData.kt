package br.com.eleanor.data

enum class Status { SUCCESS, ERROR, INFO, WARNING }
data class IntegracaoResultData(val status: Status, val msg: String?, val idFirebase: String?, val idOracle: String?)