package com.ajcordenete.data.feature.auth

import com.ajcordenete.data.core.asDomain
import com.ajcordenete.domain.error
import com.ajcordenete.domain.get
import com.ajcordenete.domain.models.AccessToken
import com.ajcordenete.domain.models.User
import com.ajcordenete.network.feature.auth.AuthRemoteSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteSource
): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<Pair<User, AccessToken>> {
        val result = authRemoteSource.login(email, password)

        return if(result.isSuccess) {
            val authData = result.get()

            Result.success(
                Pair(
                    authData.first.asDomain(),
                    authData.second.asDomain()
                )
            )
        } else {
            Result.failure(result.error())
        }
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        fullName: String,
        email: String,
        password: String
    ): Result<Pair<User, AccessToken>> {
        val result = authRemoteSource.register(
            firstName,
            lastName,
            fullName,
            email,
            password
        )

        return if(result.isSuccess) {
            val authData = result.get()

            Result.success(
                Pair(
                    authData.first.asDomain(),
                    authData.second.asDomain()
                )
            )
        } else {
            Result.failure(result.error())
        }
    }


}