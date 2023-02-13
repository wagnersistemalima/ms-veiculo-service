package br.com.sistemalima.controller.handler

import br.com.sistemalima.enum.RegrasVeiculoEnum
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
        // Dado
        val exception = VeiculoException(testMessage, RegrasVeiculoEnum.NAO_ENCONTRADO)

        // Quando
        val response = veiculoExceptionHandler.status(httpRequest, exception)

        // Então
        assertEquals(HttpStatus.NOT_FOUND, response)
        assertEquals(HttpStatus.NOT_FOUND.code, response.code)
    }

    @Test
    fun `status deve retornar 400 BAD REQUEST, quando a RegraVeiculosEnum for ESTOQUE INDISPONIVEL `() {

        // Dado
        val exception = VeiculoException(testMessage, RegrasVeiculoEnum.ESTOQUE_INDISPONIVEL)

        // Quando
        val response = veiculoExceptionHandler.status(httpRequest, exception)

        // Então
        assertEquals(HttpStatus.BAD_REQUEST, response)
        assertEquals(HttpStatus.BAD_REQUEST.code, response.code)
    }

    @Test
    fun `message deve retornar mensagem, Veiculo nao encontrado, quando a RegraVeiculosEnum for NAO ENCONTRADO `() {

        // Dado
        val exception = VeiculoException(testMessage, RegrasVeiculoEnum.NAO_ENCONTRADO)

        // Quando
        val response = veiculoExceptionHandler.mensagem(httpRequest, exception)

        // Então
        assertEquals("Veiculo não encontrado", response)

    }

    @Test
    fun `message deve retornar mensagem, Veiculo indisponivel para venda, quando a RegraVeiculosEnum for EstoqueIndisponivel `() {

        // Dado
        val exception = VeiculoException(testMessage, RegrasVeiculoEnum.ESTOQUE_INDISPONIVEL)

        // Quando
        val response = veiculoExceptionHandler.mensagem(httpRequest, exception)

        // Então
        assertEquals("Veiculo indisponivel para venda", response)

    }

    @Test
    fun `codigo deve retornar codigo 100, quando a RegraVeiculosEnum for NAO ENCONTRADO `() {

        // Dado
        val exception = VeiculoException(testMessage, RegrasVeiculoEnum.NAO_ENCONTRADO)

        // Quando
        val response = veiculoExceptionHandler.codigo(httpRequest, exception)

        // Então
        assertEquals(100, response)
    }
}