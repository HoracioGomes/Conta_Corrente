package com.projeto.contacorrente.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    fun receita(): BigDecimal{
        var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = transacao.valor?.plus(totalReceita)
            }
        }

        return totalReceita
    }

    fun despesa(): BigDecimal{
        var totalDespessa = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalDespessa = transacao.valor?.plus(totalDespessa)
            }
        }

        return totalDespessa
    }

    fun total(): BigDecimal{

        return receita().subtract(despesa())
    }
}