package com.ajcordenete.network.feature.user

import com.ajcordenete.network.feature.user.models.UserDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRemoteSource {

    suspend fun getUsers(): Result<List<UserDTO>>

    suspend fun getUsersFlow(): Flow<List<UserDTO>>
}