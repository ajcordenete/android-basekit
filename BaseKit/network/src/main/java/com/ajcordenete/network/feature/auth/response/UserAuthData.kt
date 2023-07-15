package com.ajcordenete.network.feature.auth.response

import com.ajcordenete.network.feature.user.models.UserDTO
import com.google.gson.annotations.SerializedName

data class UserAuthData(
    val user: UserDTO,
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("refresh_token")
    val refreshToken: String = ""
)