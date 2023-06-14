package com.ajcordenete.network.core

import com.google.gson.annotations.SerializedName

//Change the properties here to match the specific return error json of your API
data class BaseErrorResponse<T>(
    @SerializedName("meta") val meta: T,
    @SerializedName("message") val message: String,
    @SerializedName("http_status") val httpStatusCode: Int,
    @SerializedName("success") val success: Boolean,
    @SerializedName("error_code") val errorCode: String
)