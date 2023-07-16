package com.ajcordenete.data.core

import com.ajcordenete.domain.models.AccessToken
import com.ajcordenete.network.feature.auth.models.AccessTokenDTO
import com.ajcordenete.persistence.features.token.models.AccessTokenDB

fun AccessTokenDTO.asDomain(): AccessToken {
    this.apply {
        return AccessToken(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}

fun AccessTokenDB.asDomain(): AccessToken {
    this.apply {
        return AccessToken(
            accessToken = token.orEmpty(),
            refreshToken = refreshToken.orEmpty()
        )
    }
}

fun AccessToken.asEntity(): AccessTokenDB {
    this.apply {
        return AccessTokenDB(
            token = accessToken,
            refreshToken = refreshToken
        )
    }
}