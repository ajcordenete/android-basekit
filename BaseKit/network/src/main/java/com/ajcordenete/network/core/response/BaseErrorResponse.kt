package com.ajcordenete.network.core.response

import com.google.gson.annotations.SerializedName

//Change the properties here to match the specific return error json of your API
data class BaseErrorResponse<T>(
    @SerializedName("message") val message: String,
    @SerializedName("status") val httpStatusCode: Int,
    @SerializedName("success") val success: Boolean
)