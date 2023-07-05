package com.ajcordenete.network.feature.user

import com.ajcordenete.network.core.ErrorHandler
import com.ajcordenete.network.feature.ApiService
import com.ajcordenete.network.feature.user.models.UserDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber
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

    override suspend fun getUsersFlow(): Flow<List<UserDTO>> {
        return flow {
            val response = apiService.getUsers()
            val users = response.body() ?: emptyList()
            emit(users)
        }
            /*.catch {
            val error = ErrorHandler.handleError(it)
            Timber.i("remote error: ${error.message}")
        }*/
    }
}