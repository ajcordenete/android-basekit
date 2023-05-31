package com.ajcordenete.network.feature.user

import com.ajcordenete.network.feature.user.models.UserDTO

interface UserRemoteSource {

    suspend fun getUsers(): Result<List<UserDTO>>
}