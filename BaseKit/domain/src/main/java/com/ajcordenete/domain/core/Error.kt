package com.ajcordenete.domain.core

class Error(
    message: String = "",
    cause: Throwable? = null,
    val errorCode: String = "",
    val errorMetaJson: String? = ""
) : Throwable(message, cause)