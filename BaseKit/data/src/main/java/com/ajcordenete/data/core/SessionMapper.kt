package com.ajcordenete.data.core

import com.ajcordenete.domain.models.Session
import com.ajcordenete.persistence.features.session.models.SessionDB

fun SessionDB.asDomain(): Session {
    return Session(
        this.user.asDomain(),
        this.accessToken.asDomain()
    )
}

fun Session.asEntity(): SessionDB {
    return SessionDB(
        this.user.asEntity(),
        this.accessToken.asEntity()
    )
}

fun Session.isEmpty(): Boolean {
    return user.isEmpty() || accessToken.isEmpty()
}