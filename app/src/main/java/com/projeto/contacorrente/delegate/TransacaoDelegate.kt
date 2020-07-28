package com.projeto.contacorrente.delegate

import com.projeto.contacorrente.model.Transacao

interface TransacaoDelegate {

    fun delegate(transacao: Transacao)
}