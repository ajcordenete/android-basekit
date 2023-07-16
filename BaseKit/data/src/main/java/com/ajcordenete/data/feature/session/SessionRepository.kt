package com.ajcordenete.data.feature.session

import com.ajcordenete.domain.models.Session

interface SessionRepository {

    suspend fun getSession(): Session

    suspend fun getSessionFromLocal(): Session

    suspend fun saveSession(session: Session): Session

    suspend fun clearSession()
}