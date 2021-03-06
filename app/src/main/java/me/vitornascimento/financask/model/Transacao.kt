package me.vitornascimento.financask.model

import java.math.BigDecimal
import java.util.*

data class Transacao(
        val valor: BigDecimal,
        val categoria: String = "Indefinida",
        val tipo: TipoTransacao,
        val data: Calendar = Calendar.getInstance()
)