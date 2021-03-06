package com.projeto.contacorrente.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.projeto.contacorrente.R
import com.projeto.contacorrente.dao.TransacaoDAO
import com.projeto.contacorrente.delegate.TransacaoDelegate
import com.projeto.contacorrente.model.Tipo
import com.projeto.contacorrente.model.Transacao
import com.projeto.contacorrente.ui.ResumoView
import com.projeto.contacorrente.ui.adapter.ListaTransacoesAdapter
import com.projeto.contacorrente.ui.dialog.AdicionaTransacaoDialog
import com.projeto.contacorrente.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {
    private val dao  = TransacaoDAO()
    private val transacoes = dao.transacoes
    private val viewDaAvtivity by lazy { window.decorView }
    private val viewGroupDaActivity by lazy { viewDaAvtivity as ViewGroup }
    private val TAG = "LOG_TRANSACOES_ACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        configuraResumo()
        configuraLista()
        configuraFabs()
    }

    private fun configuraFabs() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogSubtracao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogSubtracao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
            .chama(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    adiciona(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun chamaDialogAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
            .chama(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    adiciona(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaAvtivity, transacoes)
        resumoView.atualiza()
    }


    private fun configuraLista() {
        with(lista_transacoes_listview) {
            adapter = ListaTransacoesAdapter(this@ListaTransacoesActivity, transacoes)
            setOnItemClickListener { _, _, position, _ ->
                var transacao = transacoes[position]
                chamaDialogAlteracao(transacao, position)
            }

            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(
                    Menu.NONE,
                    1,
                    Menu.NONE,
                    context.getString(R.string.string_menu_remover_transacao)
                )
            }
        }
    }

    private fun chamaDialogAlteracao(
        transacao: Transacao,
        position: Int
    ) {
        AlteraTransacaoDialog(viewDaAvtivity as ViewGroup, this).chama(
            transacao,
            object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    altera(transacao, position)
                }
            })
    }

    private fun altera(transacao: Transacao, position: Int) {
        dao.altera(position,transacao)
        atualizaTransacoes()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val idMenu = item.itemId
        val adapterContextMenuInfo =
            item.menuInfo as AdapterView.AdapterContextMenuInfo
        if (idMenu == 1) {
            remove(adapterContextMenuInfo)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(adapterContextMenuInfo: AdapterView.AdapterContextMenuInfo) {
        val posicao = adapterContextMenuInfo.position
        dao.remove(posicao)
        atualizaTransacoes()
    }

}