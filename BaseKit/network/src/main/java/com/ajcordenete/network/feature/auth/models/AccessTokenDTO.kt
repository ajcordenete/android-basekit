package com.ajcordenete.network.feature.auth.models

data class AccessTokenDTO(
    val accessToken: String = "",
    val refreshToken: String = "",
)