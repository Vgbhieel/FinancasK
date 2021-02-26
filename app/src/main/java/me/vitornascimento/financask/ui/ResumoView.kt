package me.vitornascimento.financask.ui

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import me.vitornascimento.financask.R
import me.vitornascimento.financask.extension.formataParaBrasileiro
import me.vitornascimento.financask.model.Resumo
import me.vitornascimento.financask.model.Transacao
import java.math.BigDecimal

class ResumoView(private val view: View, private val transacoes: List<Transacao>) {

    private val resumo = Resumo(transacoes)
    private val corReceitas = ContextCompat.getColor(view.context, R.color.receita)
    private val corDespesas = ContextCompat.getColor(view.context, R.color.despesa)

    fun atualiza() {
        adicionaReceitas()
        adicionaDespesas()
        adicionaTotal()
    }


    private fun adicionaReceitas() {
        val totalReceita = resumo.receita
        val resumoCardReceita = view.findViewById<TextView>(R.id.resumo_card_receita)
        with(resumoCardReceita) {
            text = totalReceita.formataParaBrasileiro()
            setTextColor(corReceitas)
        }
    }

    private fun adicionaDespesas() {
        val totalDespesas = resumo.despesa
        val resumoCardDespesa = view.findViewById<TextView>(R.id.resumo_card_despesa)
        with(resumoCardDespesa) {
            text = totalDespesas.formataParaBrasileiro()
            setTextColor(corDespesas)
        }
    }

    private fun adicionaTotal() {
        val total = resumo.total
        val resumoCardTotal = view.findViewById<TextView>(R.id.resumo_card_total)
        val cor: Int = corPor(total)
        with(resumoCardTotal) {
            text = total.formataParaBrasileiro()
            setTextColor(cor)
        }
    }

    private fun corPor(valor: BigDecimal) = if (valor >= BigDecimal.ZERO) {
        corReceitas
    } else {
        corDespesas
    }


}