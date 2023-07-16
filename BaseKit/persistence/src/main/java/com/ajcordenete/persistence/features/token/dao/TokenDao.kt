package com.ajcordenete.persistence.features.token.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajcordenete.persistence.base.BaseDao
import com.ajcordenete.persistence.features.token.models.AccessTokenDB

@Dao
abstract class TokenDao : BaseDao<AccessTokenDB> {

    @Query("SELECT * FROM ${AccessTokenDB.TOKEN_TABLE_NAME} LIMIT 1")
    abstract suspend fun getToken(): AccessTokenDB?

    @Query("DELETE FROM ${AccessTokenDB.TOKEN_TABLE_NAME}")
    abstract suspend fun deleteToken()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveToken(tokenDb: AccessTokenDB): Long
}