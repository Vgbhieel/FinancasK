package me.vitornascimento.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.R
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.extension.formataParaBrasileiro
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao
import me.vitornascimento.financask.ui.ResumoView
import me.vitornascimento.financask.ui.adapter.ListaTransacoesAdapter
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaTransacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTransacoesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumoView(view, transacoes)

        configuraListView(transacoes)

        binding.listaTransacoesAdicionaReceita.setOnClickListener {

            val viewDialog = layoutInflater.inflate(R.layout.form_transacao, null)
            AlertDialog
                    .Builder(this)
                    .setTitle(R.string.adiciona_receita)
                    .setView(viewDialog)
                    .setPositiveButton("Adicionar", null)
                    .setNegativeButton("Cancelar", null)
                    .show()

            val formDataTransacao = viewDialog.findViewById<EditText>(R.id.form_transacao_data)
            val hoje = Calendar.getInstance()
            val dia = 26
            val mes = 2
            val ano = 2021
            formDataTransacao.setText(hoje.formataParaBrasileiro())
            formDataTransacao.setOnClickListener {
                DatePickerDialog(this,
                        { _, year, month, dayOfMonth ->
                            val dataSelecionada = Calendar.getInstance()
                            dataSelecionada
                                    .set(year, month, dayOfMonth)
                            formDataTransacao
                                    .setText(dataSelecionada.formataParaBrasileiro())
                        },
                        ano, mes, dia)
                        .show()
            }

            val formCategoriaTransacao = viewDialog
                    .findViewById<Spinner>(R.id.form_transacao_categoria)
            val spinnerAdapter = ArrayAdapter
                    .createFromResource(this,
                            R.array.categorias_de_receita,
                            android.R.layout.simple_spinner_dropdown_item)
            formCategoriaTransacao.adapter = spinnerAdapter
        }
    }

    private fun configuraResumoView(view: RelativeLayout, transacoes: List<Transacao>) {
        val resumoView = ResumoView(view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraListView(transacoes: List<Transacao>) {
        val adapter = ListaTransacoesAdapter(transacoes)
        binding.listaTransacoesListview.adapter = adapter
    }

    private fun transacoesDeExemplo() = listOf(
            Transacao(
                    valor = BigDecimal(100.0), tipo = TipoTransacao.DESPESA
            ),
            Transacao(
                    BigDecimal(100.0), "Economia", TipoTransacao.RECEITA
            ),
            Transacao(
                    BigDecimal(100.0), tipo = TipoTransacao.DESPESA
            ),
            Transacao(
                    BigDecimal(100.0), categoria = "Salário", tipo = TipoTransacao.RECEITA
            )
    )

}