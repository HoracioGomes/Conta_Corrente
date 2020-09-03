package com.projeto.contacorrente.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.projeto.contacorrente.R
import com.projeto.contacorrente.delegate.TransacaoDelegate
import com.projeto.contacorrente.extension.converteParaCalendar
import com.projeto.contacorrente.extension.formataParaBrasileiro
import com.projeto.contacorrente.model.Tipo
import com.projeto.contacorrente.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

abstract class FormalarioTransacaoDialog(
    private val context: Context,
    private val viewGroup: ViewGroup?
) {
    private val viewFormTransacao = criaLayout()
    protected val campoValor = viewFormTransacao?.form_transacao_valor
    protected val campoCategoria = viewFormTransacao?.form_transacao_categoria
    protected val campoData = viewFormTransacao?.form_transacao_data
    protected abstract val tituloBtnConfirmaDialog: String
    private val TAG = "LOG_DIALOG_ADD_TRANSAC"
    fun chama(tipo: Tipo?, transacaoDelegate: TransacaoDelegate) {

        //Calendario
        configuraCampoData()

        //Spinner
       configuraSpinnerCategorias(configuraCampoCategoria(tipo))

        //Builder Dialog
        configuraFormulario(tipo, transacaoDelegate)

    }

    private fun configuraFormulario(tipo: Tipo?, transacaoDelegate: TransacaoDelegate) {
        val titulo = tituloPor(tipo)
        AlertDialog.Builder(context)
            .setTitle(titulo)
            .setView(viewFormTransacao)
            .setNegativeButton(context.getString(R.string.string_btn_cancelar), null)
            .setPositiveButton(tituloBtnConfirmaDialog) { dialog, which ->


                val valorString = campoValor?.text.toString()
                val valor = converteCampoValor(valorString)
                val dataString = campoData?.text.toString()
                val dataFormatadaFinal = dataString.converteParaCalendar(context)
                val categoriaString =
                    campoCategoria?.selectedItem.toString()

                val transacaoCriada = Transacao(
                    context, valor = valor, categoria = categoriaString,
                    tipo = tipo, data = dataFormatadaFinal
                )

                transacaoDelegate.delegate(transacaoCriada)

            }
            .show()
    }

    abstract protected fun tituloPor(tipo: Tipo?): Int

    private fun converteCampoValor(valorString: String): BigDecimal {
        val valor = try {
            BigDecimal(valorString)
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao tentar converter valor: $e")
            BigDecimal.ZERO
        }
        return valor
    }

    protected fun configuraCampoCategoria(tipo: Tipo?) : Int {

        return if (tipo == Tipo.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }


    }

    private fun configuraSpinnerCategorias(categorias: Int) {
        val adapter = ArrayAdapter
            .createFromResource(
                context, categorias,
                android.R.layout.simple_spinner_dropdown_item
            )
        campoCategoria?.adapter = adapter
    }

    private fun configuraCampoData() {
        val dataHoje = Calendar.getInstance()
        val ano = dataHoje.get(Calendar.YEAR)
        val mes = dataHoje.get(Calendar.MONTH)
        val dia = dataHoje.get(Calendar.DAY_OF_MONTH)
        campoData?.setText(dataHoje.formataParaBrasileiro())
        campoData?.setOnClickListener {
            DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(year, month, dayOfMonth)
                    campoData.setText(dataSelecionada.formataParaBrasileiro())
                }, ano, mes, dia
            )
                .show()

        }
    }

    private fun criaLayout(): View? {
        return LayoutInflater.from(context).inflate(
            R.layout.form_transacao,
            viewGroup, false
        )
    }
}