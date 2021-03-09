package me.vitornascimento.financask.delegate

import me.vitornascimento.financask.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}