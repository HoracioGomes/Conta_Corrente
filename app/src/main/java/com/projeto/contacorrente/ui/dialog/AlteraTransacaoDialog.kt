package com.projeto.contacorrente.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.projeto.contacorrente.R
import com.projeto.contacorrente.delegate.TransacaoDelegate
import com.projeto.contacorrente.extension.formataParaBrasileiro
import com.projeto.contacorrente.model.Tipo
import com.projeto.contacorrente.model.Transacao

class AlteraTransacaoDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : FormalarioTransacaoDialog(context, viewGroup) {
    override val tituloBtnConfirmaDialog: String
        get() = context.getString(R.string.string_btn_alterar)

    override fun tituloPor(tipo: Tipo?): Int {
        return if (tipo == Tipo.RECEITA) {
            R.string.altera_receita
        } else {
            R.string.altera_despesa
        }
    }

    fun chama(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
        val tipo = transacao.tipo
        //Calendario
        super.chama(tipo, transacaoDelegate)
        campoValor?.setText(transacao.valor.toString())
        campoData?.setText(transacao.data?.formataParaBrasileiro())
        val categorias = context.resources.getStringArray(configuraCampoCategoria(tipo))
        val posicaoCategoria = categorias.indexOf(transacao.categoria)
        campoCategoria?.setSelection(posicaoCategoria, true)

    }



}