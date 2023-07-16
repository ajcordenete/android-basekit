package com.ajcordenete.persistence.features.token.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AccessTokenDB.TOKEN_TABLE_NAME)
data class AccessTokenDB(
    @PrimaryKey
    val token: String = "",

    @ColumnInfo(name = "refresh_token")
    val refreshToken: String? = ""
) {

    companion object {
        const val TOKEN_TABLE_NAME = "token"
    }
}