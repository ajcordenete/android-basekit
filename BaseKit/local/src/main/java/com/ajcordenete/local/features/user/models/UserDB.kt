package com.ajcordenete.local.features.user.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajcordenete.domain.models.User

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

        fun fromDomain(user: User): UserDB {
            user.apply {
                return UserDB(
                    uid = uid,
                    fullName = fullName,
                    email = email
                )
            }
        }

        fun toDomain(userDB: UserDB): User {
            userDB.apply {
                return User(
                    uid = uid,
                    fullName = fullName.orEmpty(),
                    email = email.orEmpty()
                )
            }
        }
    }
}