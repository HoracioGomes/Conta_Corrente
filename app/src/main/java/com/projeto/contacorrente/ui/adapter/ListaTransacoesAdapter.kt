package com.projeto.contacorrente.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.projeto.contacorrente.R
import com.projeto.contacorrente.extension.formataParaBrasileiro
import com.projeto.contacorrente.model.Tipo
import com.projeto.contacorrente.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*


class ListaTransacoesAdapter(
    context: Context,
    transacoes: List<Transacao>
) : BaseAdapter() {
    val transacoes = transacoes
    val context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(
            R.layout.transacao_item,
            parent, false
        )

        if(transacoes[position].tipo == Tipo.RECEITA){
            viewCriada.transacao_valor.setTextColor(ContextCompat.getColor(context,R.color.receita))
            viewCriada.transacao_icone.setImageResource(R.drawable.icone_transacao_item_receita)
        }else if(transacoes[position].tipo == Tipo.DESPESA){
            viewCriada.transacao_valor.setTextColor(ContextCompat.getColor(context,R.color.despesa))
            viewCriada.transacao_icone.setImageResource(R.drawable.icone_transacao_item_despesa)

        }

        viewCriada.transacao_valor.text = transacoes[position].valor.toString()
        viewCriada.transacao_categoria.text = transacoes[position].categoria
        viewCriada.transacao_data.text = transacoes[position].data?.formataParaBrasileiro()
        return viewCriada
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