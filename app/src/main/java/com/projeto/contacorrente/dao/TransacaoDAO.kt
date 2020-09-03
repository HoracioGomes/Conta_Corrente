package com.projeto.contacorrente.dao

import com.projeto.contacorrente.model.Transacao

class TransacaoDAO {

    val transacoes: List<Transacao> = Companion.transacoes

    companion object {
        private val transacoes = mutableListOf<Transacao>()
    }

    fun adiciona(transacao: Transacao) {
        Companion.transacoes.add(transacao)
    }

    fun altera(posicao: Int, transacao: Transacao) {
        Companion.transacoes[posicao] = transacao
    }

    fun remove(posicao: Int) {
        Companion.transacoes.removeAt(posicao)
    }

}