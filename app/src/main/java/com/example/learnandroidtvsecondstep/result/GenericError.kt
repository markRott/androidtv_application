package com.example.learnandroidtvsecondstep.result

sealed class GenericError {
    data class HttpError(val code: Int) : GenericError()
    data class UnsupportedEndpointError(val message: String) : GenericError()
    data class UnknownError(val message: String) : GenericError()
    data class UnknownHostError(val message: String) : GenericError()
    data class ConnectionError(val message: String) : GenericError()
}
