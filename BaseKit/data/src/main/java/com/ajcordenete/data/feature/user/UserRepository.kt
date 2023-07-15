package com.ajcordenete.data.feature.user

import com.ajcordenete.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Result<List<User>>

    suspend fun updateUsersFromRemote(): Result<List<User>>

    suspend fun getUsersFlow(): Flow<List<User>>
}