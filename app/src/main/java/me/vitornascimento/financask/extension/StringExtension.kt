package me.vitornascimento.financask.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.limitaEmAte(caracteres: Int): String {
    return if (this.length > caracteres) {
        val primeiroCaracter = 0
        "${this.substring(primeiroCaracter, caracteres)}..."
    } else {
        this
    }
}

fun String.converteParaCalendar(): Calendar {

    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
    val dataParaDate = sdf.parse(this) as Date
    return Calendar.getInstance().apply {
        time = dataParaDate
    }

}