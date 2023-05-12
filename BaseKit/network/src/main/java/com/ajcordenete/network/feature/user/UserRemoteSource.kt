package com.ajcordenete.network.feature.user

import com.ajcordenete.domain.models.User

interface UserRemoteSource {

    suspend fun getUsers(): Result<List<User>>
}