package com.ajcordenete.data.feature.auth

import com.ajcordenete.domain.models.AccessToken
import com.ajcordenete.domain.models.User
import com.ajcordenete.network.feature.auth.models.AccessTokenDTO
import com.ajcordenete.network.feature.user.models.UserDTO

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<Pair<User, AccessToken>>

    suspend fun register(
        firstName: String,
        lastName: String,
        fullName: String,
        email: String,
        password: String
    ): Result<Pair<User, AccessToken>>
}