package br.com.sistemalima.exceptions

import br.com.sistemalima.RegrasVeiculoEnum

class VeiculoException: NoStacktraceException {

    val regrasVeiculoEnum: RegrasVeiculoEnum

    constructor(message: String, anRegrasVeiculoEnum: RegrasVeiculoEnum) : super(message){
        this.regrasVeiculoEnum = anRegrasVeiculoEnum
    }

    constructor(message: String, anRegrasVeiculosEnum: RegrasVeiculoEnum, throwable: Throwable) : super(message, throwable){
        this.regrasVeiculoEnum = anRegrasVeiculosEnum
    }
}