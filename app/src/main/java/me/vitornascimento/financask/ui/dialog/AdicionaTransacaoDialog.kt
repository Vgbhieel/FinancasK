package me.vitornascimento.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import me.vitornascimento.financask.R
import me.vitornascimento.financask.model.TipoTransacao

class AdicionaTransacaoDialog(
        viewGroup: ViewGroup,
        context: Context
) : FormularioTransacaoDialog(context, viewGroup) {

    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipoTransacao: TipoTransacao): Int {
        return if (tipoTransacao == TipoTransacao.RECEITA) {
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }
    }

}