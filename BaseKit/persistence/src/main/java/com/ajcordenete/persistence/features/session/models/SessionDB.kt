package com.ajcordenete.persistence.features.session.models

import com.ajcordenete.persistence.features.token.models.AccessTokenDB
import com.ajcordenete.persistence.features.user.models.UserDB

data class SessionDB(
    var user: UserDB,
    var accessToken: AccessTokenDB,
)