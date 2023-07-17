package com.ajcordenete.persistence.features.session

import com.ajcordenete.persistence.features.session.models.SessionDB

interface SessionLocalSource {

    suspend fun getSession(): SessionDB?

    suspend fun getSessionFromLocal(): SessionDB?

    suspend fun saveSession(session: SessionDB): SessionDB

    suspend fun clearSession()
}