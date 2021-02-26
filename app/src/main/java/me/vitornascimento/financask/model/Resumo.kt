package me.vitornascimento.financask.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somaPor(TipoTransacao.RECEITA)

    val despesa get() = somaPor(TipoTransacao.DESPESA)

    val total: BigDecimal
        get() = receita.subtract(despesa)


    private fun somaPor(tipo: TipoTransacao): BigDecimal {
        val somaDeTransacoesPeloTipo = transacoes
                .filter {
                    it.tipo == tipo
                }
                .sumByDouble {
                    it.valor.toDouble()
                }

        return BigDecimal(somaDeTransacoesPeloTipo)
    }
}