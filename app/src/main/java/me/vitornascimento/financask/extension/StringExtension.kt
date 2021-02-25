package me.vitornascimento.financask.extension

fun String.limitaEmAte(caracteres: Int): String {
    return if (this.length > caracteres) {
        val primeiroCaracter = 0
        "${this.substring(primeiroCaracter, caracteres)}..."
    } else {
        this
    }
}