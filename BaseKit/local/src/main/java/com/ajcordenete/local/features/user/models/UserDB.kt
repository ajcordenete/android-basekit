package com.ajcordenete.local.features.user.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserDB.USER_TABLE_NAME)
data class UserDB(
    @PrimaryKey
    var uid: String = "",
    @ColumnInfo(name = "full_name")
    var fullName: String? = "",
    @ColumnInfo(name = "first_name")
    var firstName: String? = "",
    @ColumnInfo(name = "last_name")
    var lastName: String? = "",
    var email: String? = "",
) {

    companion object {
        const val USER_TABLE_NAME = "user"
    }
}