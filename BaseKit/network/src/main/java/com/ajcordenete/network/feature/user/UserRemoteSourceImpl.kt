package com.ajcordenete.network.feature.user

import com.ajcordenete.network.core.ErrorHandler
import com.ajcordenete.network.feature.ApiService
import com.ajcordenete.network.feature.user.models.UserDTO
import javax.inject.Inject

class UserRemoteSourceImpl @Inject constructor(
    private val apiService: ApiService
): UserRemoteSource {

    override suspend fun getUsers(): Result<List<UserDTO>> {
        return try {
            val response = apiService.getUsers()

            val users = response.body() ?: emptyList()
            Result.success(users)

        } catch (e: Exception) {
            Result.failure(
                ErrorHandler.handleError(e)
            )
        }
    }
}