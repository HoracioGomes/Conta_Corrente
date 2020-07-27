package com.projeto.contacorrente.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.projeto.contacorrente.R
import com.projeto.contacorrente.extension.formataParaBrasileiro
import com.projeto.contacorrente.model.Tipo
import com.projeto.contacorrente.model.Transacao
import com.projeto.contacorrente.ui.ResumoView
import com.projeto.contacorrente.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        var transacoes: List<Transacao> = geraTransacoesDeExemplo()
        configuraResumo(transacoes)
        configuraLista(transacoes)
        lista_transacoes_adiciona_receita.setOnClickListener {
            val view: View = window.decorView
            val viewFormTransacao = LayoutInflater.from(this).inflate(
                R.layout.form_transacao,
                view as ViewGroup, false
            )

            //Calendario
            val dataHoje = Calendar.getInstance().formataParaBrasileiro()
            var ano = 2020
            var mes = 6
            var dia = 27
            viewFormTransacao.form_transacao_data.setText(dataHoje)
            viewFormTransacao.form_transacao_data.setOnClickListener {
                DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { calendarView, year, month, dayOfMonth ->
                        var dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(year, month, dayOfMonth)
                        viewFormTransacao.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                    }, ano, mes, dia
                )
                    .show()

            }

            //Spinner
            val adapter = ArrayAdapter
                .createFromResource(
                    this, R.array.categorias_de_receita,
                    android.R.layout.simple_spinner_dropdown_item
                )
            viewFormTransacao.form_transacao_categoria.adapter = adapter

            //Builder Dialog
            AlertDialog.Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setView(viewFormTransacao)
                .setNegativeButton(getString(R.string.string_btn_cancelar), null)
                .setPositiveButton(getString(R.string.string_btn_adicionar), null)
                .show()
        }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view: View = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
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