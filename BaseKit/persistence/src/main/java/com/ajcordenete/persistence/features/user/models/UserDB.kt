package com.ajcordenete.persistence.features.user.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserDB.USER_TABLE_NAME)
data class UserDB(
    @PrimaryKey
    var id: String = "",

    @ColumnInfo("first_name")
    val firstName: String? = "",

    @ColumnInfo("last_name")
    val lastName: String? = "",

    @ColumnInfo(name = "full_name")
    var fullName: String? = "",

    @ColumnInfo(name = "email")
    var email: String? = "",
) {

    companion object {
        const val USER_TABLE_NAME = "user"
    }
}