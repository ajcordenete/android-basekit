package com.ajcordenete.data

import com.ajcordenete.data.feature.auth.AuthRepository
import com.ajcordenete.data.feature.auth.AuthRepositoryImpl
import com.ajcordenete.data.feature.session.SessionRepository
import com.ajcordenete.data.feature.session.SessionRepositoryImpl
import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.data.feature.user.UserRepositoryImpl
import com.ajcordenete.network.feature.auth.AuthRemoteSource
import com.ajcordenete.network.feature.user.UserRemoteSource
import com.ajcordenete.persistence.features.token.AccessTokenLocalSource
import com.ajcordenete.persistence.features.user.UserLocalSource
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
    @Provides
    @Singleton
    fun providesAuthRepository(
        authRemoteSource: AuthRemoteSource
    ): AuthRepository {
        return AuthRepositoryImpl(authRemoteSource)
    }

    @Provides
    @Singleton
    fun providesSessionRepository(
        userLocalSource: UserLocalSource,
        tokenLocalSource: AccessTokenLocalSource
    ): SessionRepository {
        return SessionRepositoryImpl(userLocalSource, tokenLocalSource)
    }
}