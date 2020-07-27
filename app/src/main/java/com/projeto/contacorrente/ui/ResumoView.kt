package com.projeto.contacorrente.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.projeto.contacorrente.R
import com.projeto.contacorrente.extension.formataParaBrasileiro
import com.projeto.contacorrente.model.Resumo
import com.projeto.contacorrente.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {
    private val resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza() {
        adicionaReceitaResumo()
        adicionaDespesaResumo()
        adicionaTotalResumo()
    }

    private fun adicionaReceitaResumo() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }
    }


    private fun adicionaDespesaResumo() {
        var totalDespesa = resumo.despesa
        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }

    private fun adicionaTotalResumo() {
        var total = resumo.total
        val cor = if (total >= BigDecimal.ZERO) {
            corReceita
        } else {
            corDespesa
        }
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }
    }
}