package com.projeto.contacorrente.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projeto.contacorrente.R
import com.projeto.contacorrente.model.Tipo
import com.projeto.contacorrente.model.Transacao
import com.projeto.contacorrente.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        var transacoes = listOf(
            Transacao(
                this,
                BigDecimal(50.0),
                Tipo.DESPESA
            ), Transacao(
                ctx = this,
                valor = BigDecimal(100.0),
                categoria = "Venda",
                tipo = Tipo.RECEITA
            )
        )
        val adapterTransacoes = ListaTransacoesAdapter(this, transacoes)
        lista_transacoes_listview.adapter = adapterTransacoes
    }


}