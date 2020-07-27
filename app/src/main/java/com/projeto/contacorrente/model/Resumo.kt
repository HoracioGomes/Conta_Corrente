package com.projeto.contacorrente.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita: BigDecimal
        get() = somaPor(Tipo.RECEITA)
    val despesa: BigDecimal
        get() = somaPor(Tipo.DESPESA)
    val total: BigDecimal
        get() = receita.subtract(despesa)

    private fun somaPor(tipo: Tipo): BigDecimal {
        return BigDecimal(transacoes.filter { it.tipo == tipo }
            .sumByDouble { transacao -> transacao.valor.toDouble() })
    }
}