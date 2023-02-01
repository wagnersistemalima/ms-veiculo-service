package br.com.sistemalima.repository

import br.com.sistemalima.model.Veiculo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface VeiculoRepository: JpaRepository<Veiculo, Long> {
}