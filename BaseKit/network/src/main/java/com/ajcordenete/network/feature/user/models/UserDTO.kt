package com.ajcordenete.network.feature.user.models

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @field:SerializedName("id")
    val id: Long?,
    @field:SerializedName("first_name")
    val firstName: String?,
    @field:SerializedName("last_name")
    val lastName: String?,
    @field:SerializedName("full_name")
    val fullName: String?,
    @field:SerializedName("email")
    val email: String?
)