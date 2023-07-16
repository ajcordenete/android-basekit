package com.ajcordenete.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val fullName: String = "",
    val email: String = "",
): Parcelable {

    companion object {
        fun empty(): User {
            return User(
                id = "",
                firstName = "",
                lastName = "",
                fullName = "",
                email = ""
            )
        }
    }
}