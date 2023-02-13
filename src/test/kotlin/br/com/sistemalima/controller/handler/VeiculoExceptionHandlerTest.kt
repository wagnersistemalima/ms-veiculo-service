package br.com.sistemalima.controller.handler

import br.com.sistemalima.RegrasVeiculoEnum
import br.com.sistemalima.exceptions.VeiculoException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class VeiculoExceptionHandlerTest {

    private val veiculoExceptionHandler: VeiculoExceptionHandler = VeiculoExceptionHandler()
    private val httpRequest = mockk<HttpRequest<String?>>()
    private val testMessage = "testMessage"


    @Test
    fun `status deve retornar 404 NOT FOUND, quando a RegraVeiculosEnum for NAO ENCONTRADO `() {
        val exception = VeiculoException(testMessage, RegrasVeiculoEnum.NAO_ENCONTRADO)
        val response = veiculoExceptionHandler.status(httpRequest, exception)

        assertEquals(HttpStatus.NOT_FOUND, response)
        assertEquals(HttpStatus.NOT_FOUND.code, response.code)
    }

    @Test
    fun `message deve retornar mensagem, Veiculo nao encontrado, quando a RegraVeiculosEnum for NAO ENCONTRADO `() {
        val exception = VeiculoException(testMessage, RegrasVeiculoEnum.NAO_ENCONTRADO)
        val response = veiculoExceptionHandler.mensagem(httpRequest, exception)

        assertEquals("Veiculo não encontrado", response)

    }

    @Test
    fun `codigo deve retornar codigo 100, quando a RegraVeiculosEnum for NAO ENCONTRADO `() {
        val exception = VeiculoException(testMessage, RegrasVeiculoEnum.NAO_ENCONTRADO)
        val response = veiculoExceptionHandler.codigo(httpRequest, exception)

        assertEquals(100, response)
    }
}