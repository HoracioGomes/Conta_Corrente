package com.projeto.contacorrente.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.projeto.contacorrente.R

class ListaTransacoesAdapter(
    context: Context,
    transacoes: List<String>
) : BaseAdapter() {
    val transacoes = transacoes
    val context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.transacao_item,parent,false)
    }

    override fun getItem(position: Int): String {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}