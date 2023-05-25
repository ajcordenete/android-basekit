package com.ajcordenete.data.feature.user

import com.ajcordenete.data.core.asDomain
import com.ajcordenete.data.core.asEntity
import com.ajcordenete.domain.get
import com.ajcordenete.domain.models.User
import com.ajcordenete.persistence.features.user.UserLocalSource
import com.ajcordenete.network.feature.user.UserRemoteSource
import java.lang.Exception
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

    override suspend fun updateUsersFromRemote(): List<User> {
        val resultUsers = userRemoteSource.getUsers()
        return if(resultUsers.isSuccess) {
            val users = resultUsers.get().map { userDTO ->
                userDTO.asDomain()
            }

            //update local cache
            insertUsers(users)

            users
        } else {
            listOf()
        }
    }

    private suspend fun insertUsers(users: List<User>) {
        userLocalSource.insertUsers(
            users.map { user ->
                user.asEntity()
            }
        )
    }

}