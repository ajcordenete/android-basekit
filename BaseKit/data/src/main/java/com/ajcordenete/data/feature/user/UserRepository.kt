package com.ajcordenete.data.feature.user

import com.ajcordenete.domain.models.User

interface UserRepository {

    suspend fun getUsers(): Result<List<User>>

    suspend fun updateUsersFromRemote(): List<User>
}