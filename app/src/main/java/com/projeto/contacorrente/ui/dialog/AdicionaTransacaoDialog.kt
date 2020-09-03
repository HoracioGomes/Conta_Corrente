package com.projeto.contacorrente.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.projeto.contacorrente.R
import com.projeto.contacorrente.model.Tipo

class AdicionaTransacaoDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : FormalarioTransacaoDialog(context, viewGroup) {
    override val tituloBtnConfirmaDialog: String
        get() = context.getString(R.string.string_btn_adicionar)

    override fun tituloPor(tipo: com.projeto.contacorrente.model.Tipo?): kotlin.Int {
        return if (tipo == Tipo.RECEITA) {
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }
    }
}
