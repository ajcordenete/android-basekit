package com.ajcordenete.persistence.features.token

import com.ajcordenete.persistence.features.token.dao.TokenDao
import com.ajcordenete.persistence.features.token.models.AccessTokenDB
import javax.inject.Inject

class AccessTokenLocalSourceImpl @Inject constructor(
    private val tokenDao: TokenDao
) : AccessTokenLocalSource {

    override suspend fun getAccessToken(): AccessTokenDB? {
        return tokenDao.getToken()
    }

    override suspend fun saveAccessToken(accessToken: AccessTokenDB): AccessTokenDB {
        tokenDao.deleteToken()
        tokenDao.saveToken(accessToken)
        return accessToken;
    }

    override suspend fun deleteToken() {
        tokenDao.deleteToken()
    }


}