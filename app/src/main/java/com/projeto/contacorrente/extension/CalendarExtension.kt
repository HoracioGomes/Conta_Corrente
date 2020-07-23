package com.projeto.contacorrente.extension

import android.content.Context
import com.projeto.contacorrente.R
import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro(): String {
    val format =
        SimpleDateFormat("dd/MM/yyyy")
    return format.format(this.time)
}