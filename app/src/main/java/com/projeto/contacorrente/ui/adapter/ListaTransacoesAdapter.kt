package com.projeto.contacorrente.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.projeto.contacorrente.R
import com.projeto.contacorrente.extension.formataParaBrasileiro
import com.projeto.contacorrente.extension.limitaEmAte
import com.projeto.contacorrente.model.Tipo
import com.projeto.contacorrente.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*


class ListaTransacoesAdapter(
    var context: Context,
    var transacoes: List<Transacao>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(
            R.layout.transacao_item,
            parent, false
        )

        adicionaValorEIcone(position, viewCriada)
        adicionaCategoria(viewCriada, position)
        adicionaData(viewCriada, position)
        return viewCriada
    }

    private fun adicionaData(viewCriada: View, position: Int) {
        viewCriada.transacao_data.text = transacoes[position].data?.formataParaBrasileiro()
    }

    private fun adicionaCategoria(viewCriada: View, position: Int) {
        viewCriada.transacao_categoria.text = transacoes[position].categoria?.limitaEmAte(14)
    }

    private fun adicionaValorEIcone(position: Int, viewCriada: View) {
        if (transacoes[position].tipo == Tipo.RECEITA) {
            viewCriada.transacao_valor.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.receita
                )
            )
            viewCriada.transacao_icone.setImageResource(R.drawable.icone_transacao_item_receita)
        } else if (transacoes[position].tipo == Tipo.DESPESA) {
            viewCriada.transacao_valor.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.despesa
                )
            )
            viewCriada.transacao_icone.setImageResource(R.drawable.icone_transacao_item_despesa)

        }

        viewCriada.transacao_valor.text = transacoes[position].valor?.formataParaBrasileiro()
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

}