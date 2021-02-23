package me.vitornascimento.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.financask.databinding.ActivityListaTransacoesBinding
import me.vitornascimento.financask.ui.adapter.ListaTransacoesAdapter

class ListaTransacoesActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaTransacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTransacoesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val transacoes = listOf("Teste 1", "Teste 2")
        val adapter = ListaTransacoesAdapter(transacoes, this)
        binding.listaTransacoesListview.adapter = adapter

    }

}