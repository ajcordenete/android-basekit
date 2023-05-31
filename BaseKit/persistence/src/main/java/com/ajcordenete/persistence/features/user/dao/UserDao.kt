package com.ajcordenete.persistence.features.user.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajcordenete.persistence.base.BaseDao
import com.ajcordenete.persistence.features.user.models.UserDB

@Dao
abstract class UserDao: BaseDao<UserDB>  {

    @Query("SELECT * FROM ${UserDB.USER_TABLE_NAME} LIMIT 1")
    abstract suspend fun getUser(): UserDB?

    @Query("SELECT * FROM ${UserDB.USER_TABLE_NAME}")
    abstract suspend fun getUsers(): List<UserDB>?

    @Query("DELETE FROM ${UserDB.USER_TABLE_NAME}")
    abstract suspend fun deleteUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveUser(dbUser: UserDB): Long
}