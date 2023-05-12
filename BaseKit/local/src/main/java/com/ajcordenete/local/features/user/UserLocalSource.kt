package com.ajcordenete.local.features.user

import com.ajcordenete.local.features.user.models.UserDB

interface UserLocalSource {

    suspend fun insertUser(userDB: UserDB)

    suspend fun getAllUsers(): List<UserDB>
}