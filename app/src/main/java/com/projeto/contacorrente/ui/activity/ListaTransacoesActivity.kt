package com.projeto.contacorrente.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projeto.contacorrente.R
import com.projeto.contacorrente.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        var transacoes = listOf("valor1 - 10,00", "valor2 - 20,00")
        val adapterTransacoes= ListaTransacoesAdapter(this,transacoes)
        lista_transacoes_listview.adapter = adapterTransacoes
    }


}