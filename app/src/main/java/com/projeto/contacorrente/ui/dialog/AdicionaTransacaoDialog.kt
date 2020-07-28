package com.projeto.contacorrente.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.projeto.contacorrente.R
import com.projeto.contacorrente.delegate.TransacaoDelegate
import com.projeto.contacorrente.extension.converteParaCalendar
import com.projeto.contacorrente.extension.formataParaBrasileiro
import com.projeto.contacorrente.model.Tipo
import com.projeto.contacorrente.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class AdicionaTransacaoDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) {
    private val viewFormTransacao = criaLayout()
    private val TAG = "LOG_DIALOG_ADD_TRANSAC"
    fun configuraDialog(transacaoDelegate: TransacaoDelegate) {

        //Calendario
        configuraCampoData()

        //Spinner
        configuraCampoCategoria()

        //Builder Dialog
        configuraFormulario(transacaoDelegate)
    }

    private fun configuraFormulario(transacaoDelegate: TransacaoDelegate) {
        AlertDialog.Builder(context)
            .setTitle(R.string.adiciona_receita)
            .setView(viewFormTransacao)
            .setNegativeButton(context.getString(R.string.string_btn_cancelar), null)
            .setPositiveButton(context.getString(R.string.string_btn_adicionar)) { dialog, which ->

                val valorString = viewFormTransacao?.form_transacao_valor?.text.toString()
                val valor = converteCampoValor(valorString)
                val dataString = viewFormTransacao?.form_transacao_data?.text.toString()
                val dataFormatadaFinal = dataString.converteParaCalendar(context)
                val categoriaString =
                    viewFormTransacao?.form_transacao_categoria?.selectedItem.toString()

                val transacaoCriada = Transacao(
                    context, valor = valor, categoria = categoriaString,
                    tipo = Tipo.RECEITA, data = dataFormatadaFinal
                )

                transacaoDelegate.delegate(transacaoCriada)

            }
            .show()
    }

    private fun converteCampoValor(valorString: String): BigDecimal {
        val valor = try {
            BigDecimal(valorString)
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao tentar converter valor: $e")
            BigDecimal.ZERO
        }
        return valor
    }

    private fun configuraCampoCategoria() {
        val adapter = ArrayAdapter
            .createFromResource(
                context, R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item
            )
        viewFormTransacao?.form_transacao_categoria?.adapter = adapter
    }

    private fun configuraCampoData() {
        val dataHoje = Calendar.getInstance()
        var ano = dataHoje.get(Calendar.YEAR)
        var mes = dataHoje.get(Calendar.MONTH)
        var dia = dataHoje.get(Calendar.DAY_OF_MONTH)
        viewFormTransacao?.form_transacao_data?.setText(dataHoje.formataParaBrasileiro())
        viewFormTransacao?.form_transacao_data?.setOnClickListener {
            DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { calendarView, year, month, dayOfMonth ->
                    var dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(year, month, dayOfMonth)
                    viewFormTransacao.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                }, ano, mes, dia
            )
                .show()

        }
    }

    private fun criaLayout(): View? {
        val viewFormTransacao = LayoutInflater.from(context).inflate(
            R.layout.form_transacao,
            viewGroup, false
        )
        return viewFormTransacao
    }
}