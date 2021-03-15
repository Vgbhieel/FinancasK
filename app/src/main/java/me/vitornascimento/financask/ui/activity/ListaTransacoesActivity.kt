package me.vitornascimento.financask.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao
import me.vitornascimento.financask.ui.ResumoView
import me.vitornascimento.financask.ui.adapter.ListaTransacoesAdapter
import me.vitornascimento.financask.ui.dialog.AdicionaTransacaoDialog
import me.vitornascimento.financask.ui.dialog.AlteraTransacaoDialog

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    lateinit var binding: ActivityListaTransacoesBinding
    private val view: ViewGroup by lazy {
        binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTransacoesBinding.inflate(layoutInflater)
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
                .chama(tipoTransacao) { transacaoCriada ->
                    adiciona(transacaoCriada)
                    binding.listaTransacoesAdicionaMenu.close(true)
                }
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
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val idDoMenu = item.itemId
        if (idDoMenu == 1) {
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoDaTransacao = adapterMenuInfo.position
            remove(posicaoDaTransacao)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(posicao: Int) {
        transacoes.removeAt(posicao)
        atualizaTransacoes()
    }

    private fun chamaDialogDeAlteracaoDeTransacao(
            transacao: Transacao,
            position: Int
    ) {
        AlteraTransacaoDialog(view, this)
                .chama(transacao) { transacaoAlterada ->
                    altera(transacaoAlterada, position)
                }
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