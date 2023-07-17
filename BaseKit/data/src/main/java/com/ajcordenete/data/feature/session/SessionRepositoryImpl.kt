package com.ajcordenete.data.feature.session

import com.ajcordenete.data.core.asDomain
import com.ajcordenete.data.core.asEntity
import com.ajcordenete.domain.models.AccessToken
import com.ajcordenete.domain.models.Session
import com.ajcordenete.domain.models.User
import com.ajcordenete.persistence.features.session.SessionLocalSource
import com.ajcordenete.persistence.features.token.AccessTokenLocalSource
import com.ajcordenete.persistence.features.user.UserLocalSource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionLocalSource: SessionLocalSource
): SessionRepository {

    private var session: Session? = null

    override suspend fun getSession(): Session {
        if(session == null) {
            this.session = getSessionFromLocal()
        }
        return session!!
    }

    override suspend fun getSessionFromLocal(): Session {
        val sessionLocal = sessionLocalSource.getSession()

        sessionLocal?.let {
            return it.asDomain()
        }

        return Session(
            User.empty(),
            AccessToken.empty()
        )
    }

    override suspend fun saveSession(session: Session): Session {
        return sessionLocalSource
            .saveSession(session.asEntity())
            .asDomain()
    }

    override suspend fun clearSession() {
        sessionLocalSource.clearSession()
    }

}