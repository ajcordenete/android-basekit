package com.ajcordenete.data.core

import com.ajcordenete.domain.models.AccessToken
import com.ajcordenete.network.feature.auth.models.AccessTokenDTO

fun AccessTokenDTO.asDomain(): AccessToken {
    this.apply {
        return AccessToken(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}