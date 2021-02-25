package me.vitornascimento.financask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import me.vitornascimento.financask.R
import me.vitornascimento.financask.extension.formataParaBrasileiro
import me.vitornascimento.financask.extension.limitaEmAte
import me.vitornascimento.financask.model.TipoTransacao
import me.vitornascimento.financask.model.Transacao

class ListaTransacoesAdapter(
        private val transacoes: List<Transacao>
) : BaseAdapter() {

    private val limiteDeCaracteres = 14

    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.transacao_item, parent, false)

        val campoValor = view.findViewById<MaterialTextView>(R.id.transacao_valor)
        val campoCategoria = view.findViewById<MaterialTextView>(R.id.transacao_categoria)
        val campoData = view.findViewById<MaterialTextView>(R.id.transacao_data)
        val iconeTransacao = view.findViewById<ImageView>(R.id.transacao_icone)

        val transacaoAtual = getItem(position)

        when (transacaoAtual.tipo) {

            TipoTransacao.RECEITA -> {
                if (parent != null) {
                    campoValor.setTextColor(ContextCompat.getColor(parent.context, R.color.receita))
                    iconeTransacao.setImageResource(R.drawable.icone_transacao_item_receita)
                }
            }

            TipoTransacao.DESPESA -> {
                if (parent != null) {
                    campoValor.setTextColor(ContextCompat.getColor(parent.context, R.color.despesa))
                    iconeTransacao.setImageResource(R.drawable.icone_transacao_item_despesa)
                }
            }

        }

        campoValor.text = transacaoAtual.valor.formataParaBrasileiro()
        campoCategoria.text = transacaoAtual.categoria.limitaEmAte(limiteDeCaracteres)
        campoData.text = transacaoAtual.data.formataParaBrasileiro()

        return view
    }
}