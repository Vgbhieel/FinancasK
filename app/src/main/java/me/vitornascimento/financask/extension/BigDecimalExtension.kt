package me.vitornascimento.financask.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formataParaBrasileiro(): String {
    val decimalFormat = DecimalFormat
            .getCurrencyInstance(Locale("pt", "BR"))
    return decimalFormat.format(this)
}