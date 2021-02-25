package me.vitornascimento.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.android.material.textview.MaterialTextView
import me.vitornascimento.financask.R
import me.vitornascimento.financask.model.Transacao

class ListaTransacoesAdapter(
        private val transacoes: List<Transacao>,
        private val context: Context
) : BaseAdapter() {


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
                .from(context)
                .inflate(R.layout.transacao_item, parent, false)

        val campoValor = view.findViewById<MaterialTextView>(R.id.transacao_valor)
        val campoCategoria = view.findViewById<MaterialTextView>(R.id.transacao_categoria)
        val campoData = view.findViewById<MaterialTextView>(R.id.transacao_data)

        val transacaoAtual = getItem(position)

        campoValor.setText(transacaoAtual.valor.toString())
        campoCategoria.setText(transacaoAtual.categoria)
        campoData.setText(transacaoAtual.data.time.toString())


        return view
    }
}