package com.ajcordenete.persistence

import android.app.Application
import com.ajcordenete.persistence.features.token.AccessTokenLocalSource
import com.ajcordenete.persistence.features.token.AccessTokenLocalSourceImpl
import com.ajcordenete.persistence.features.user.UserLocalSource
import com.ajcordenete.persistence.features.user.UserLocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application.applicationContext)
    }

    @Provides
    @Singleton
    fun providesUserLocalSource(appDatabase: AppDatabase): UserLocalSource {
        return UserLocalSourceImpl(appDatabase.userDao())
    }

    @Provides
    @Singleton
    fun providesTokenLocalSource(appDatabase: AppDatabase): AccessTokenLocalSource {
        return AccessTokenLocalSourceImpl(appDatabase.tokenDao())
    }
}