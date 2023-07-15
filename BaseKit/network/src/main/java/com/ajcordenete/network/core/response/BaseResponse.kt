package com.ajcordenete.network.core.response

open class BaseResponse(
    val success: Boolean = false,
    val status: Int = 500,
    val message: String = ""
)
