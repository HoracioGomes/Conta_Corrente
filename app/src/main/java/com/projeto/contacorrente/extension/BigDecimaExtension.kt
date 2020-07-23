package com.projeto.contacorrente.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formataParaBrasileiro(): String{
    return DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
        .format(this).replace("R$", "R$ ")
}