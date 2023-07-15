package com.ajcordenete.network.core.request

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

open class BaseRemoteSource {

    fun getJsonRequestBody(jsonString: String) =
        jsonString.toRequestBody("application/json".toMediaTypeOrNull())
}