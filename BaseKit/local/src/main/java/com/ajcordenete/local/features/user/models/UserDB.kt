package com.ajcordenete.local.features.user.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajcordenete.domain.models.User

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

        fun fromDomain(user: User): UserDB {
            user.apply {
                return UserDB(
                    uid = uid,
                    fullName = fullName,
                    firstName = firstName,
                    lastName = lastName,
                    email = email
                )
            }
        }

        fun toDomain(userDB: UserDB): User {
            userDB.apply {
                return User(
                    uid = uid,
                    fullName = fullName.orEmpty(),
                    firstName = firstName.orEmpty(),
                    lastName = lastName.orEmpty(),
                    email = email.orEmpty()
                )
            }
        }
    }
}