package com.ajcordenete.data

import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.data.feature.user.UserRepositoryImpl
import com.ajcordenete.persistence.features.user.UserLocalSource
import com.ajcordenete.network.feature.user.UserRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(
        userRemoteSource: UserRemoteSource,
        userLocalSource: UserLocalSource
    ): UserRepository {
        return UserRepositoryImpl(
            userRemoteSource,
            userLocalSource
        )
    }
}