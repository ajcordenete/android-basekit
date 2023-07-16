package com.ajcordenete.persistence.features.token

import com.ajcordenete.persistence.features.token.models.AccessTokenDB

interface AccessTokenLocalSource {

    suspend fun getAccessToken(): AccessTokenDB?

    suspend fun saveAccessToken(accessToken: AccessTokenDB): AccessTokenDB

    suspend fun deleteToken()
}