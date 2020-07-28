package com.projeto.contacorrente.model

import android.content.Context
import com.projeto.contacorrente.R
import java.math.BigDecimal
import java.util.*

class Transacao(
    var ctx: Context?,
    var valor: BigDecimal = BigDecimal.ZERO,
    var categoria: String? = ctx?.getString(R.string.string_indefinido),
    var tipo: Tipo?,
    var data: Calendar? = Calendar.getInstance()
) {

    constructor(ctx: Context?, valor: BigDecimal, tipo: Tipo?)
            : this(
        ctx = ctx,
        valor = valor,
        categoria = ctx?.getString(R.string.string_indefinido),
        tipo = tipo
    )

    constructor(ctx: Context?, valor: BigDecimal, tipo: Tipo?, data: Calendar?)
            : this(
        ctx = ctx, valor = valor, categoria = ctx?.getString(R.string.string_indefinido),
        tipo = tipo, data = data
    )

    override fun toString(): String {
        return "Valor: $valor - Categoria: $categoria - Tipo: $tipo - Data: ${data?.time}"
    }
}