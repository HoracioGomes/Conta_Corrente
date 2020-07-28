package com.projeto.contacorrente.extension

import android.content.Context
import com.projeto.contacorrente.R
import java.text.SimpleDateFormat
import java.util.*


fun String.limitaEmAte(caracteres: Int): String {
    val caractereInicial = 0
    if (this.length > caracteres) {
        return "${this.substring(caractereInicial, caracteres)}..."
    }
    return this
}

fun String.converteParaCalendar(context: Context): Calendar {
    val sdf = SimpleDateFormat(context.getString(R.string.string_data_padrao_brasileiro))
    val dataFormatada = sdf.parse(this)
    val dataFormatadaFinal = Calendar.getInstance()
    dataFormatadaFinal.time = dataFormatada
    return dataFormatadaFinal
}
