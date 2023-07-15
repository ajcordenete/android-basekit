package com.ajcordenete.network.feature

import com.ajcordenete.network.core.response.BaseDataResponse
import com.ajcordenete.network.feature.auth.response.UserAuthData
import com.ajcordenete.network.feature.user.models.UserDTO
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserDTO>>

    @POST("auth/login")
    suspend fun login(
        @Body requestBody: RequestBody
    ): BaseDataResponse<UserAuthData>
}