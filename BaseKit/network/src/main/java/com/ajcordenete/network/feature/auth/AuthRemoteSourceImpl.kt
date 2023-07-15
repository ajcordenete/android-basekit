package com.ajcordenete.network.feature.auth

import com.ajcordenete.network.core.ErrorHandler
import com.ajcordenete.network.core.request.BaseRemoteSource
import com.ajcordenete.network.feature.ApiService
import com.ajcordenete.network.feature.auth.models.AccessTokenDTO
import com.ajcordenete.network.feature.user.models.UserDTO
import com.google.gson.JsonObject
import javax.inject.Inject

class AuthRemoteSourceImpl@Inject constructor(
    private val apiService: ApiService
): AuthRemoteSource, BaseRemoteSource()  {

    override suspend fun login(
        email: String,
        password: String
    ): Result<Pair<UserDTO, AccessTokenDTO>> {
        val loginBody = JsonObject()
            .apply {
                if (email.isNotEmpty()) addProperty("email", email)
                if (password.isNotEmpty()) addProperty("password", password)
            }

        val requestBody = getJsonRequestBody(loginBody.toString())

        return try {
            val response = apiService.login(requestBody)
            val user = response.data.user
            val accessToken = AccessTokenDTO(response.data.accessToken, response.data.refreshToken)
            Result.success(
                Pair(
                    user,
                    accessToken
                )
            )
        } catch (e: Exception) {
            Result.failure(
                ErrorHandler.handleError(e)
            )
        }
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        fullName: String,
        email: String,
        password: String
    ): Result<Pair<UserDTO, AccessTokenDTO>> {
        val registerBody = JsonObject()
            .apply {
                if (email.isNotEmpty()) addProperty("first_name", firstName)
                if (email.isNotEmpty()) addProperty("last_name", lastName)
                if (email.isNotEmpty()) addProperty("full_name", fullName)
                if (email.isNotEmpty()) addProperty("email", email)
                if (password.isNotEmpty()) addProperty("password", password)
            }

        val requestBody = getJsonRequestBody(registerBody.toString())

        return try {
            val response = apiService.register(requestBody)
            val user = response.data.user
            val accessToken = AccessTokenDTO(response.data.accessToken, response.data.refreshToken)
            Result.success(
                Pair(
                    user,
                    accessToken
                )
            )
        } catch (e: Exception) {
            Result.failure(
                ErrorHandler.handleError(e)
            )
        }
    }


}