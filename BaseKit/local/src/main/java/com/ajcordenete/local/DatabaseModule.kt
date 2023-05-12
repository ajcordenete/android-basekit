package com.ajcordenete.local

import android.app.Application
import com.ajcordenete.local.features.user.UserLocalSource
import com.ajcordenete.local.features.user.UserLocalSourceImpl
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
}