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
        var transacoes:List<Transacao> = geraTransacoesDeExemplo()
        configuraLista(transacoes)
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(this, transacoes)
    }

    private fun geraTransacoesDeExemplo(): List<Transacao> {
        return listOf(
            Transacao(
                this,
                BigDecimal(50.0),
                Tipo.DESPESA
            ), Transacao(
                ctx = this,
                valor = BigDecimal(100.0),
                categoria = "Venda",
                tipo = Tipo.RECEITA
            ),
            Transacao(
                ctx = this,
                valor = BigDecimal(90.0),
                categoria = "Venda",
                tipo = Tipo.RECEITA
            ),
            Transacao(
                ctx = this,
                valor = BigDecimal(60.0),
                categoria = "Almoço no restaurate Andrade",
                tipo = Tipo.DESPESA
            )
        )
    }


}