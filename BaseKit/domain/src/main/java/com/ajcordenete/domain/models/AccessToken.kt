package com.ajcordenete.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccessToken(
    val accessToken: String = "",
    val refreshToken: String = "",
): Parcelable {

    companion object {
        fun empty(): AccessToken {
            return AccessToken(
                accessToken = "",
                refreshToken = ""
            )
        }
    }
}