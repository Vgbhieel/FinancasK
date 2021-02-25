package me.vitornascimento.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao
import me.vitornascimento.financask.ui.adapter.ListaTransacoesAdapter
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaTransacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTransacoesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val transacoes: List<Transacao> = transacoesDeExemplo()
        configuraListView(transacoes)
    }

    private fun configuraListView(transacoes: List<Transacao>) {
        val adapter = ListaTransacoesAdapter(transacoes)
        binding.listaTransacoesListview.adapter = adapter
    }

    private fun transacoesDeExemplo() = listOf(
            Transacao(
                    valor = BigDecimal(20.5), tipo = TipoTransacao.DESPESA
            ),
            Transacao(
                    BigDecimal(100.0), "Economia", TipoTransacao.RECEITA
            ),
            Transacao(
                    BigDecimal(200.0), tipo = TipoTransacao.DESPESA
            ),
            Transacao(
                    BigDecimal(500.0), categoria = "Salário", tipo = TipoTransacao.RECEITA
            )
    )

}