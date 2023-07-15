package com.ajcordenete.network.core.response

data class BaseDataResponse<T>(
    val data: T
): BaseResponse()