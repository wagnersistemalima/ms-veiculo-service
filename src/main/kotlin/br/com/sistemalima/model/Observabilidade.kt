package br.com.sistemalima.model

import java.util.UUID

data class Observabilidade(
    val modelo: String,
    val marca: String,
    val placa: String,
    val correlationId: String = UUID.randomUUID().toString()
)
