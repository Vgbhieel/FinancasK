package me.vitornascimento.financask.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import me.vitornascimento.financask.R
import me.vitornascimento.financask.extension.converteParaCalendar
import me.vitornascimento.financask.extension.formataParaBrasileiro
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao
import java.math.BigDecimal
import java.util.*

abstract class FormularioTransacaoDialog(
        private val context: Context,
        private val viewGroup: ViewGroup) {

    private val viewDialog = criaLayout()
    protected val formDataTransacao = viewDialog
            .findViewById<EditText>(R.id.form_transacao_data)
    protected val formCategoriaTransacao = viewDialog
            .findViewById<Spinner>(R.id.form_transacao_categoria)
    protected val formValorTransacao = viewDialog
            .findViewById<EditText>(R.id.form_transacao_valor)
    protected abstract val tituloBotaoPositivo: String

    fun chama(tipoTransacao: TipoTransacao, delegate: (transacao: Transacao) -> Unit) {

        configuraCampoData()

        configuraCampoCategoria(tipoTransacao)

        configuraFormulario(tipoTransacao, delegate)
    }

    private fun configuraFormulario(
            tipoTransacao: TipoTransacao,
            delegate: (transacao: Transacao) -> Unit
    ) {

        val titulo = tituloPor(tipoTransacao)

        AlertDialog
                .Builder(context)
                .setTitle(titulo)
                .setView(viewDialog)
                .setPositiveButton(tituloBotaoPositivo) { _, _ ->

                    val valorEmString = formValorTransacao
                            .text.toString()

                    val dataEmString = formDataTransacao
                            .text.toString()

                    val categoriaEmString = formCategoriaTransacao
                            .selectedItem.toString()

                    val valorEmBigDecimal = converteCampoValor(valorEmString)

                    val dataEmCalendar = dataEmString.converteParaCalendar()


                    val transacaoCriada = Transacao(
                            tipo = tipoTransacao,
                            valor = valorEmBigDecimal,
                            data = dataEmCalendar,
                            categoria = categoriaEmString
                    )

                    delegate(transacaoCriada)


                }
                .setNegativeButton("Cancelar", null)
                .show()
    }

    protected abstract fun tituloPor(tipoTransacao: TipoTransacao): Int

    private fun converteCampoValor(valorEmTexto: String) = try {
        BigDecimal(valorEmTexto)
    } catch (e: NumberFormatException) {
        Toast
                .makeText(context, "Erro ao converter o valor.", Toast.LENGTH_LONG)
                .show()

        BigDecimal.ZERO
    }

    private fun configuraCampoCategoria(tipoTransacao: TipoTransacao) {

        val categorias = categoriasPor(tipoTransacao)

        val spinnerAdapter = ArrayAdapter
                .createFromResource(
                        context,
                        categorias,
                        android.R.layout.simple_spinner_dropdown_item
                )
        formCategoriaTransacao.adapter = spinnerAdapter
    }

    protected fun categoriasPor(tipoTransacao: TipoTransacao) =
            if (tipoTransacao == TipoTransacao.RECEITA) {
                R.array.categorias_de_receita
            } else {
                R.array.categorias_de_despesa
            }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()
        val dia = hoje.get(Calendar.DAY_OF_MONTH)
        val mes = hoje.get(Calendar.MONTH)
        val ano = hoje.get(Calendar.YEAR)

        formDataTransacao.setText(hoje.formataParaBrasileiro())
        formDataTransacao.setOnClickListener {
            DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada
                                .set(year, month, dayOfMonth)
                        formDataTransacao
                                .setText(dataSelecionada.formataParaBrasileiro())
                    },
                    ano, mes, dia
            )
                    .show()
        }
    }

    private fun criaLayout(): View {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.form_transacao, viewGroup, false)
    }
}