package me.vitornascimento.financask.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.delegate.TransacaoDelegate
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao
import me.vitornascimento.financask.ui.ResumoView
import me.vitornascimento.financask.ui.adapter.ListaTransacoesAdapter
import me.vitornascimento.financask.ui.dialog.AdicionaTransacaoDialog
import me.vitornascimento.financask.ui.dialog.AlteraTransacaoDialog

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    lateinit var binding: ActivityListaTransacoesBinding
    private lateinit var view: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTransacoesBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        configuraResumoView()
        configuraListView()
        configuraFab(view)
    }

    private fun configuraFab(view: ViewGroup) {
        binding.listaTransacoesAdicionaReceita
            .setOnClickListener {
                chamaDialogDeAdicaoDeTransacao(view, TipoTransacao.RECEITA)
            }

        binding.listaTransacoesAdicionaDespesa
            .setOnClickListener {
                chamaDialogDeAdicaoDeTransacao(view, TipoTransacao.DESPESA)
            }
    }

    private fun chamaDialogDeAdicaoDeTransacao(view: ViewGroup, tipoTransacao: TipoTransacao) {
        AdicionaTransacaoDialog(view, this)
            .chama(tipoTransacao, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    adiciona(transacao)
                    binding.listaTransacoesAdicionaMenu.close(true)
                }
            })
    }

    private fun atualizaTransacoes() {
        configuraListView()
        configuraResumoView()
    }

    private fun configuraResumoView() {
        val resumoView = ResumoView(view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraListView() {
        val adapter = ListaTransacoesAdapter(transacoes)
        with(binding.listaTransacoesListview) {
            this.adapter = adapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogDeAlteracaoDeTransacao(transacao, position)
            }
        }
    }

    private fun chamaDialogDeAlteracaoDeTransacao(
        transacao: Transacao,
        position: Int
    ) {
        AlteraTransacaoDialog(view, this)
            .chama(transacao, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    altera(transacao, position)
                }
            })
    }

    private fun adiciona(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        transacoes[posicao] = transacao
        atualizaTransacoes()
    }

}