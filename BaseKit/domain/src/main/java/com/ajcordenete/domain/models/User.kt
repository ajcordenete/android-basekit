package com.ajcordenete.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uid: String = "",
    val fullName: String = "",
    val email: String = "",
): Parcelable