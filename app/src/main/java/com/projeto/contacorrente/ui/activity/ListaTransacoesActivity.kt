package com.projeto.contacorrente.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.projeto.contacorrente.R
import com.projeto.contacorrente.delegate.TransacaoDelegate
import com.projeto.contacorrente.model.Transacao
import com.projeto.contacorrente.ui.ResumoView
import com.projeto.contacorrente.ui.adapter.ListaTransacoesAdapter
import com.projeto.contacorrente.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {
    private val transacoes: MutableList<Transacao> = mutableListOf()
    private val TAG = "LOG_TRANSACOES_ACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        configuraResumo()
        configuraLista()
        lista_transacoes_adiciona_receita.setOnClickListener {
            AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                .configuraDialog(object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
        }
    }

    private fun atualizaTransacoes(transacaoCriada: Transacao) {
        transacoes.add(transacaoCriada)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view: View = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }


    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(this, transacoes)
    }

}