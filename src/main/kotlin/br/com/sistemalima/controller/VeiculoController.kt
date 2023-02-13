package br.com.sistemalima.controller

import br.com.sistemalima.model.Observabilidade
import br.com.sistemalima.model.Veiculo
import br.com.sistemalima.service.VeiculoService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import org.slf4j.LoggerFactory
import java.util.*

@Controller(value = "/veiculos", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
class VeiculoController(
    private val veiculoService: VeiculoService
) {

    private val logger = LoggerFactory.getLogger(VeiculoController::class.java)

    companion object {
        private val tag = "class: VeiculoController"
        private val tagCreate = "method: create [POST]"
        private val tagFindById = "method: findById [GET]"
        private val tagStartMessage = "Inicio do processo request"
        private val tagEndMessage = "Fim do processo request"
    }

    @Post
    fun create(@Body veiculo: Veiculo) : HttpResponse<Veiculo> {
        val observabilidade = Observabilidade(veiculo.modelo, veiculo.marca, veiculo.placa)
        logger.info(String.format("$tag, $tagCreate, $tagStartMessage: $observabilidade"))
        veiculoService.create(veiculo, observabilidade)
        logger.info(String.format("$tag, $tagCreate, $tagEndMessage: $observabilidade"))
        return HttpResponse.created(veiculo)
    }

    @Get(value = "/{id}")
    fun findById(@PathVariable id: Long): HttpResponse<Veiculo> {
        val correlationId = UUID.randomUUID().toString()
        logger.info(String.format("$tag, $tagFindById, $tagStartMessage, id: $id, correlationId= $correlationId"))
        val veiculo = veiculoService.findById(id, correlationId)
        logger.info(String.format("$tag, $tagFindById, $tagEndMessage, id: $id, correlationId= $correlationId"))
        return HttpResponse.ok(veiculo)
    }
}