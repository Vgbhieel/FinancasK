package me.vitornascimento.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.delegate.TransacaoDelegate
import me.vitornascimento.financask.dialog.AdicionaTransacaoDialog
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao
import me.vitornascimento.financask.ui.ResumoView
import me.vitornascimento.financask.ui.adapter.ListaTransacoesAdapter

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

        binding.listaTransacoesAdicionaReceita
                .setOnClickListener {
                    AdicionaTransacaoDialog(view, this)
                            .configuraAlertDialog(TipoTransacao.RECEITA, object : TransacaoDelegate {
                                override fun delegate(transacao: Transacao) {
                                    adicionaTransacao(transacao)
                                    binding.listaTransacoesAdicionaMenu.close(true)
                                }
                            })
                }

        binding.listaTransacoesAdicionaDespesa
                .setOnClickListener {
                    AdicionaTransacaoDialog(view, this)
                            .configuraAlertDialog(TipoTransacao.DESPESA, object : TransacaoDelegate {
                                override fun delegate(transacao: Transacao) {
                                    adicionaTransacao(transacao)
                                    binding.listaTransacoesAdicionaMenu.close(true)
                                }
                            })
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