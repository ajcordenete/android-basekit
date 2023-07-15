package com.ajcordenete.network

import com.ajcordenete.network.feature.ApiService
import com.ajcordenete.network.feature.auth.AuthRemoteSource
import com.ajcordenete.network.feature.auth.AuthRemoteSourceImpl
import com.ajcordenete.network.feature.user.UserRemoteSource
import com.ajcordenete.network.feature.user.UserRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteSourceModule {

    @Provides
    @Singleton
    fun providesUserRemoteSource(apiService: ApiService): UserRemoteSource {
        return UserRemoteSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesAuthRemoteSource(apiService: ApiService): AuthRemoteSource {
        return AuthRemoteSourceImpl(apiService)
    }
}