package me.vitornascimento.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.R
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.extension.formataParaBrasileiro
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao
import me.vitornascimento.financask.ui.ResumoView
import me.vitornascimento.financask.ui.adapter.ListaTransacoesAdapter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    lateinit var binding: ActivityListaTransacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTransacoesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configuraResumoView()

        configuraListView()

        binding.listaTransacoesAdicionaReceita.setOnClickListener {

            val viewDialog = layoutInflater.inflate(R.layout.form_transacao, null)

            val formDataTransacao = viewDialog
                    .findViewById<EditText>(R.id.form_transacao_data)
            val formCategoriaTransacao = viewDialog
                    .findViewById<Spinner>(R.id.form_transacao_categoria)
            val formValorTransacao = viewDialog
                    .findViewById<EditText>(R.id.form_transacao_valor)

            AlertDialog
                    .Builder(this)
                    .setTitle(R.string.adiciona_receita)
                    .setView(viewDialog)
                    .setPositiveButton("Adicionar") { _, _ ->

                        val valorEmString = formValorTransacao
                                .text.toString()

                        val dataEmString = formDataTransacao
                                .text.toString()

                        val categoriaEmString = formCategoriaTransacao
                                .selectedItem.toString()

                        val valorEmBigDecimal = try {
                            BigDecimal(valorEmString)
                        } catch (e: NumberFormatException) {
                            Toast
                                    .makeText(this, "Erro ao converter o valor.", Toast.LENGTH_LONG)
                                    .show()

                            BigDecimal.ZERO
                        }

                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
                        val dataParaDate = sdf.parse(dataEmString) as Date
                        val dataParaCalendar = Calendar.getInstance().apply {
                            time = dataParaDate
                        }

                        val transacaoCriada = Transacao(tipo = TipoTransacao.RECEITA,
                                valor = valorEmBigDecimal,
                                data = dataParaCalendar,
                                categoria = categoriaEmString)

                        adicionaTransacao(transacaoCriada)
                        binding.listaTransacoesAdicionaMenu.close(true)
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()


            val hoje = Calendar.getInstance()
            val dia = hoje.get(Calendar.DAY_OF_MONTH)
            val mes = hoje.get(Calendar.MONTH)
            val ano = hoje.get(Calendar.YEAR)
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

            val spinnerAdapter = ArrayAdapter
                    .createFromResource(this,
                            R.array.categorias_de_receita,
                            android.R.layout.simple_spinner_dropdown_item)
            formCategoriaTransacao.adapter = spinnerAdapter
        }
    }

    private fun adicionaTransacao(transacao: Transacao) {
        transacoes.add(transacao)
        configuraListView()
        configuraResumoView()
    }

    private fun configuraResumoView() {
        val resumoView = ResumoView(binding.root, transacoes)
        resumoView.atualiza()
    }

    private fun configuraListView() {
        val adapter = ListaTransacoesAdapter(transacoes)
        binding.listaTransacoesListview.adapter = adapter
    }

}