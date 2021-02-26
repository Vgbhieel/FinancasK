package me.vitornascimento.financask.ui

import android.view.View
import android.widget.TextView
import me.vitornascimento.financask.R
import me.vitornascimento.financask.extension.formataParaBrasileiro
import me.vitornascimento.financask.model.Resumo
import me.vitornascimento.financask.model.Transacao

class ResumoView(private val view: View, private val transacoes: List<Transacao>) {

    private val resumo = Resumo(transacoes)

    fun adicionaReceitas() {
        val totalReceita = resumo.receita()
        val resumoCardReceita = view.findViewById<TextView>(R.id.resumo_card_receita)
        resumoCardReceita.text = totalReceita.formataParaBrasileiro()
    }

    fun adicionaDespesas() {
        val totalDespesas = resumo.despesa()
        val resumoCardDespesa = view.findViewById<TextView>(R.id.resumo_card_despesa)
        resumoCardDespesa.text = totalDespesas.formataParaBrasileiro()
    }

    fun adicionaTotal() {
        val total = resumo.total()
        val resumoCardTotal = view.findViewById<TextView>(R.id.resumo_card_total)
        resumoCardTotal.text = total.formataParaBrasileiro()
    }


}