package com.ajcordenete.data.feature.auth

import com.ajcordenete.domain.models.AccessToken
import com.ajcordenete.domain.models.User

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<Pair<User, AccessToken>>
}