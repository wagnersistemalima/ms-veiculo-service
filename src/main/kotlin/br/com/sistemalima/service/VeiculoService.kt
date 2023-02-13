package br.com.sistemalima.service

import br.com.sistemalima.enum.RegrasVeiculoEnum
import br.com.sistemalima.exceptions.InternalGenericException
import br.com.sistemalima.exceptions.VeiculoException
import br.com.sistemalima.model.Observabilidade
import br.com.sistemalima.model.Veiculo
import br.com.sistemalima.repository.VeiculoRepository
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class VeiculoService(
    private val veiculoRepository: VeiculoRepository
) {

    private val logger = LoggerFactory.getLogger(VeiculoService::class.java)

    companion object {
        private val tag = "class: VeiculoService"
        private val tagCreate = "method: create"
        private val tagFindById = "method findById"
        private val tagMovimentRequest = "Movimentação do processo request"
        private val veiculoExceptionMessage = "Id do veiculo não encontrado na base"
        private val internalGenericExceptionMessage = "Falha no servidor interno"
    }

    fun create(veiculo: Veiculo, observabilidade: Observabilidade): Veiculo {
        logger.info(String.format("$tag, $tagCreate, $tagMovimentRequest: $observabilidade"))
        return veiculoRepository.save(veiculo)

    }

    fun findById(id: Long, correlationId: String): Veiculo {
        logger.info(String.format("$tag, $tagFindById, $tagMovimentRequest, id: $id, correlationId: $correlationId"))

        try {
            return veiculoRepository.findById(id).get()

        }catch (ex: NoSuchElementException) {
            logger.error(String.format("Error: $tag, $tagFindById, id: $id, correlationId: $correlationId," +
                    " exceptionMessage: ${ex.message}, message: $veiculoExceptionMessage cause: ${ex.cause}"))
            throw VeiculoException(ex.message.toString(), RegrasVeiculoEnum.NAO_ENCONTRADO, ex)
        }catch (ex: Exception) {
            logger.error(String.format("Error: $tag, $tagFindById, id: $id, correlationId: $correlationId," +
                    " exceptionMessage: ${ex.message}, message: $internalGenericExceptionMessage cause: ${ex.cause}"))
            throw InternalGenericException(ex.message.toString(), ex)
        }
    }
}