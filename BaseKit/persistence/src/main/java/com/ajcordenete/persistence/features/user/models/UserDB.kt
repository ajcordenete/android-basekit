package com.ajcordenete.persistence.features.user.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserDB.USER_TABLE_NAME)
data class UserDB(
    @PrimaryKey
    var uid: String = "",
    @ColumnInfo(name = "name")
    var fullName: String? = "",
    @ColumnInfo(name = "email")
    var email: String? = "",
) {

    companion object {
        const val USER_TABLE_NAME = "user"
    }
}