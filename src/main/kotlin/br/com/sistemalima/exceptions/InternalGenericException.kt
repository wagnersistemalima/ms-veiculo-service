package br.com.sistemalima.exceptions

class InternalGenericException: NoStacktraceException {
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}