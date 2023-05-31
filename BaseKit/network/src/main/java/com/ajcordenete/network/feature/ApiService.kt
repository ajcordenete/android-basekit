package com.ajcordenete.network.feature

import com.ajcordenete.network.feature.user.models.UserDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserDTO>>
}