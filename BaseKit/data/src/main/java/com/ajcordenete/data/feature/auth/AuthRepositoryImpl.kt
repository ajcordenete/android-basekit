package com.ajcordenete.data.feature.auth

import com.ajcordenete.data.core.asDomain
import com.ajcordenete.data.core.asEntity
import com.ajcordenete.domain.error
import com.ajcordenete.domain.get
import com.ajcordenete.domain.models.AccessToken
import com.ajcordenete.domain.models.Session
import com.ajcordenete.domain.models.User
import com.ajcordenete.network.feature.auth.AuthRemoteSource
import com.ajcordenete.persistence.features.session.SessionLocalSource
import com.ajcordenete.persistence.features.session.models.SessionDB
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteSource,
    private val sessionLocalSource: SessionLocalSource
): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<Pair<User, AccessToken>> {
        val result = authRemoteSource.login(email, password)

        return if(result.isSuccess) {
            val authData = result.get()

            val resultPair = Pair(
                authData.first.asDomain(),
                authData.second.asDomain()
            )

            saveAuthDataToSession(resultPair)

            Result.success(resultPair)
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

            val resultPair = Pair(
                authData.first.asDomain(),
                authData.second.asDomain()
            )

            saveAuthDataToSession(resultPair)

            Result.success(resultPair)
        } else {
            Result.failure(result.error())
        }
    }

    private suspend fun saveAuthDataToSession(pair: Pair<User, AccessToken>): Session {
        val session = Session(pair.first, pair.second)
        sessionLocalSource.saveSession(session.asEntity())

        return session
    }


}