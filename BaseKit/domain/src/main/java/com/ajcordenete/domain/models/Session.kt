package com.ajcordenete.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    var user: User,
    var accessToken: AccessToken,
): Parcelable