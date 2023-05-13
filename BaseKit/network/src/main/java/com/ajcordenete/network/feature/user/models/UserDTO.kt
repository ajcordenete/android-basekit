package com.ajcordenete.network.feature.user.models

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id")
    val uid: String = "",
    val name: String = "",
    val email: String = "",
)