package com.ajcordenete.persistence.features.session

import com.ajcordenete.persistence.features.session.models.SessionDB
import com.ajcordenete.persistence.features.token.AccessTokenLocalSource
import com.ajcordenete.persistence.features.user.UserLocalSource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SessionLocalSourceImpl @Inject constructor(
    private val userLocalSource: UserLocalSource,
    private val tokenLocalSource: AccessTokenLocalSource
): SessionLocalSource {

    private var session: SessionDB? = null

    override suspend fun getSession(): SessionDB? {
        if(session == null) {
            this.session = getSessionFromLocal()
        }
        return session
    }

    override suspend fun getSessionFromLocal(): SessionDB? = coroutineScope {
        val user = async { userLocalSource.getUser() }
        val accessToken = async { tokenLocalSource.getAccessToken() }

        if(user.await() != null && accessToken.await() != null) {
            return@coroutineScope SessionDB(
                user.await()!!,
                accessToken.await()!!
            )
        }
        return@coroutineScope null
    }

    override suspend fun saveSession(session: SessionDB): SessionDB = coroutineScope{
        val user = async { userLocalSource.insertUser(session.user) }
        val accessToken = async { tokenLocalSource.saveAccessToken(session.accessToken) }

        return@coroutineScope SessionDB(
            user.await(),
            accessToken.await()
        )
    }

    override suspend fun clearSession() {
        userLocalSource.deleteUsers()
        tokenLocalSource.deleteToken()
    }
}