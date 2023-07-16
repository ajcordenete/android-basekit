package com.ajcordenete.persistence.features.user

import com.ajcordenete.persistence.features.user.dao.UserDao
import com.ajcordenete.persistence.features.user.models.UserDB
import javax.inject.Inject

class UserLocalSourceImpl @Inject constructor(
    private val userDao: UserDao
): UserLocalSource {

    override suspend fun insertUser(userDB: UserDB): UserDB {
        userDao.insert(userDB)
        return userDB
    }

    override suspend fun insertUsers(usersDB: List<UserDB>) {
        userDao.insert(usersDB.toMutableList())
    }

    override suspend fun getUser(): UserDB? {
        return userDao.getUser()
    }

    override suspend fun getAllUsers(): List<UserDB> {
        return userDao.getUsers().orEmpty()
    }

    override suspend fun deleteUsers() {
        userDao.deleteUsers()
    }


}