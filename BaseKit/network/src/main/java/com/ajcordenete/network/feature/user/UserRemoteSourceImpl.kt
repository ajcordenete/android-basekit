package com.ajcordenete.network.feature.user

import com.ajcordenete.network.feature.ApiService
import com.ajcordenete.network.feature.user.models.UserDTO
import javax.inject.Inject

class UserRemoteSourceImpl @Inject constructor(
    private val apiService: ApiService
): UserRemoteSource {

    override suspend fun getUsers(): Result<List<UserDTO>> {
        return try {
            val response = apiService.getUsers()

            return if (response.isSuccessful) {
                val users = response.body() ?: emptyList()
                Result.success(users)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(
                Exception("Generic Error")
            )
        }
    }
}