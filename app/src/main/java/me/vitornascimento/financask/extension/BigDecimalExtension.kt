package me.vitornascimento.financask.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formataParaBrasileiro(): String {

    val moeda = DecimalFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
    return moeda.replace("-R$ ", "R$ -")

}