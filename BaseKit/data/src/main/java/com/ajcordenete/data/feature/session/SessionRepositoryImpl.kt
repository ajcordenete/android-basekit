package com.ajcordenete.data.feature.session

import com.ajcordenete.data.core.asDomain
import com.ajcordenete.data.core.asEntity
import com.ajcordenete.domain.models.AccessToken
import com.ajcordenete.domain.models.Session
import com.ajcordenete.domain.models.User
import com.ajcordenete.persistence.features.token.AccessTokenLocalSource
import com.ajcordenete.persistence.features.user.UserLocalSource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val userLocalSource: UserLocalSource,
    private val tokenLocalSource: AccessTokenLocalSource
): SessionRepository {

    var session: Session? = null

    override suspend fun getSession(): Session {
        if(session == null) {
            this.session = getSessionFromLocal()
        }
        return session!!
    }

    override suspend fun getSessionFromLocal(): Session = coroutineScope {
        val user = async { userLocalSource.getUser()?.asDomain() }
        val accessToken = async { tokenLocalSource.getAccessToken()?.asDomain() }

        return@coroutineScope Session(
            user.await() ?: User.empty(),
            accessToken.await() ?: AccessToken.empty()
        )
    }

    override suspend fun saveSession(session: Session): Session = coroutineScope{
        val user = async { userLocalSource.insertUser(session.user.asEntity()) }
        val accessToken = async { tokenLocalSource.saveAccessToken(session.accessToken.asEntity()) }

        return@coroutineScope Session(
            user.await().asDomain(),
            accessToken.await().asDomain()
        )
    }

    override suspend fun clearSession() {
        userLocalSource.deleteUsers()
        tokenLocalSource.deleteToken()
        this.session = null
    }

}