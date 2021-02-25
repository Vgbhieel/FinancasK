package me.vitornascimento.financask.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro(): String {
    val sdf = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale("pt", "BR")
    )
    return sdf.format(this.time)
}