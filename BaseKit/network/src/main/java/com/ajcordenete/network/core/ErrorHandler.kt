package com.ajcordenete.network.core

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import com.ajcordenete.domain.core.Error
import com.ajcordenete.network.core.response.BaseErrorResponse
import timber.log.Timber

class ErrorHandler {

    companion object {
        fun handleError(error: Throwable): Error {
            return when (error) {
                is HttpException -> {
                    val gson = Gson()
                    val body = error.response()!!.errorBody()
                    val errorResponse =
                        gson.fromJson(body!!.string(), BaseErrorResponse::class.java)
                    val message = if (errorResponse.message.isNotEmpty()) {
                        errorResponse.message
                    } else {
                        "Network error"
                    }

                    Error(
                        message,
                        error
                    )
                }
                is IOException -> {
                    Error("Please check your internet connection", error)
                }
                else -> {
                    Timber.e(error)
                    Error("Oops! Something went wrong", error)
                }
            }
        }
    }
}