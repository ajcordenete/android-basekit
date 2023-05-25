package com.ajcordenete.persistence.features.user

import com.ajcordenete.persistence.features.user.models.UserDB

interface UserLocalSource {

    suspend fun insertUser(userDB: UserDB)
    suspend fun insertUsers(usersDB: List<UserDB>)
    suspend fun getAllUsers(): List<UserDB>
}