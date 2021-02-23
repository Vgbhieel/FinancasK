package me.vitornascimento.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import me.vitornascimento.financask.R

class ListaTransacoesAdapter(
    private val transacoes: List<String>,
    private val context: Context
) : BaseAdapter() {


    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getItem(position: Int): String {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
    }
}