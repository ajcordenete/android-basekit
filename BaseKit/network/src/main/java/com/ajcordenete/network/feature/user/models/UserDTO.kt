package com.ajcordenete.network.feature.user.models

import com.ajcordenete.domain.models.User
import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id")
    val uid: String = "",
    val name: String = "",
    val email: String = "",
) {
    companion object {

        fun toDomain(userDTO: UserDTO): User {
            userDTO.apply {
                return User(
                    uid = uid,
                    fullName = name,
                    email = email
                )
            }
        }
    }
}