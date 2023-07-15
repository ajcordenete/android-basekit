package com.ajcordenete.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long = 0L,
    val firstName: String = "",
    val lastName: String = "",
    val fullName: String = "",
    val email: String = "",
): Parcelable