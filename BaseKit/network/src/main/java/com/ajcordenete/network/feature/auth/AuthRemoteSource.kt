package com.ajcordenete.network.feature.auth

import com.ajcordenete.network.feature.auth.models.AccessTokenDTO
import com.ajcordenete.network.feature.user.models.UserDTO

interface AuthRemoteSource {

    suspend fun login(
        email: String,
        password: String
    ): Result<Pair<UserDTO, AccessTokenDTO>>
}