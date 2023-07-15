package com.ajcordenete.domain.models

data class AccessToken(
    val accessToken: String = "",
    val refreshToken: String = "",
)