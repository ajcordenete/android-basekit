package com.ajcordenete.data.feature.user

import com.ajcordenete.data.core.asDomain
import com.ajcordenete.data.core.asEntity
import com.ajcordenete.domain.error
import com.ajcordenete.domain.get
import com.ajcordenete.domain.models.User
import com.ajcordenete.network.feature.user.UserRemoteSource
import com.ajcordenete.persistence.features.user.UserLocalSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource,
    private val userLocalSource: UserLocalSource
): UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val users = userLocalSource.getAllUsers()
            Result.success(users.map { userDB ->
                userDB.asDomain()
            })
        } catch (e: Exception) {
            Result.failure(e)
            //TODO add our own exception handler here
        }
    }

    override suspend fun updateUsersFromRemote(): Result<List<User>> {
        val resultUsers = userRemoteSource.getUsers()

        return if(resultUsers.isSuccess) {
            val users = resultUsers.get().map { userDTO ->
                userDTO.asDomain()
            }
            //update local cache
            insertUsers(users)

            Result.success(users)
        } else {
            Result.failure(resultUsers.error())
        }
    }

    private suspend fun insertUsers(users: List<User>) = withContext(Dispatchers.IO) {
        userLocalSource.insertUsers(
            users.map { user ->
                user.asEntity()
            }
        )
    }

    override suspend fun getUsersFlow(): Flow<List<User>> {
        return userRemoteSource
            .getUsersFlow()
            .map { usersDTO ->
                usersDTO.map {
                    it.asDomain()
                }
            }
            .flowOn(Dispatchers.IO)
    }
}