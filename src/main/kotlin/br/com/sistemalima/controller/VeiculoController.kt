package br.com.sistemalima.controller

import br.com.sistemalima.model.Veiculo
import br.com.sistemalima.service.VeiculoService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller("/veiculos")
class VeiculoController(
    private val veiculoService: VeiculoService
) {

    @Post
    fun create(@Body veiculo: Veiculo) : HttpResponse<Veiculo> {
        veiculoService.create(veiculo)
        return HttpResponse.created(veiculo)
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): HttpResponse<Veiculo> {
        val veiculo = veiculoService.findById(id)
        return HttpResponse.ok<Veiculo?>().body(veiculo)
    }
}