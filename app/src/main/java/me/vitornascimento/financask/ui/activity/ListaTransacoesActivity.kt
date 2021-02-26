package me.vitornascimento.financask.ui.activity

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao
import me.vitornascimento.financask.ui.ResumoView
import me.vitornascimento.financask.ui.adapter.ListaTransacoesAdapter
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaTransacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTransacoesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumoView(view, transacoes)

        configuraListView(transacoes)
    }

    private fun configuraResumoView(view: RelativeLayout, transacoes: List<Transacao>) {
        val resumoView = ResumoView(view, transacoes)
        resumoView.adicionaReceitas()
        resumoView.adicionaDespesas()
        resumoView.adicionaTotal()
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