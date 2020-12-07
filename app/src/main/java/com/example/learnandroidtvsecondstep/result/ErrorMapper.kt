package com.example.learnandroidtvsecondstep.result

import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

object ErrorMapper {

    fun map(error: Throwable): GenericError {

        val message = error.message ?: ""
        return when (error) {
            is HttpException -> GenericError.HttpError(error.code())
            is UnsupportedOperationException -> GenericError.UnsupportedEndpointError(message)
            is IOException -> GenericError.ConnectionError(message)
            is UnknownHostException -> GenericError.UnknownHostError(message)
            else -> GenericError.UnknownError(message)
        }
    }

}