package me.vitornascimento.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.model.Transacao
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

        val transacoes = listOf(
                Transacao(
                        BigDecimal(20.5), "Comida", Calendar.getInstance()
                ),
                Transacao(
                        BigDecimal(100.0), "Economia", Calendar.getInstance()
                )
        )

        val adapter = ListaTransacoesAdapter(transacoes, this)

        binding.listaTransacoesListview.adapter = adapter
    }

}