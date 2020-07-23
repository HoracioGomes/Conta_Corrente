package com.projeto.contacorrente.extension


fun String.limitaEmAte(caracteres: Int): String {
    val caractereInicial = 0
    if (this.length > caracteres) {
        return "${this.substring(caractereInicial, caracteres)}..."
    }
    return this
}
