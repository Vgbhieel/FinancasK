package me.vitornascimento.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.android.material.textview.MaterialTextView
import me.vitornascimento.financask.R
import me.vitornascimento.financask.model.Transacao
import java.text.SimpleDateFormat
import java.util.*

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

        campoValor.text = transacaoAtual.valor.toString()

        campoCategoria.text = transacaoAtual.categoria

        val formatToBrazil = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        campoData.text = formatToBrazil.format(transacaoAtual.data.time)


        return view
    }
}