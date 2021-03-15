package me.vitornascimento.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import me.vitornascimento.financask.R
import me.vitornascimento.financask.extension.formataParaBrasileiro
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao

class AlteraTransacaoDialog(
        viewGroup: ViewGroup,
        private val context: Context
) : FormularioTransacaoDialog(context, viewGroup) {

    fun chama(transacao: Transacao, delegate: (transacao: Transacao) -> Unit) {

        super.chama(transacao.tipo, delegate)

        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCategoria(transacao)
    }

    private fun inicializaCampoCategoria(transacao: Transacao) {
        val posicaoCategoria = context.resources.getStringArray(categoriasPor(transacao.tipo))
                .run {
                    indexOf(transacao.categoria)
                }
        formCategoriaTransacao.setSelection(posicaoCategoria, true)
    }

    private fun inicializaCampoData(transacao: Transacao) {
        formDataTransacao.setText(transacao.data.formataParaBrasileiro())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        formValorTransacao.setText(transacao.valor.toString())
    }

    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipoTransacao: TipoTransacao): Int {
        return if (tipoTransacao == TipoTransacao.RECEITA) {
            R.string.altera_receita
        } else {
            R.string.altera_despesa
        }
    }
}