package com.ajcordenete.local.features.user

import com.ajcordenete.local.features.user.dao.UserDao
import com.ajcordenete.local.features.user.models.UserDB
import javax.inject.Inject

class UserLocalSourceImpl @Inject constructor(
    private val userDao: UserDao
): UserLocalSource {

    override suspend fun insertUser(userDB: UserDB) {
        userDao.insert(userDB)
    }

    override suspend fun getAllUsers(): List<UserDB> {
        return userDao.getUsers().orEmpty()
    }


}