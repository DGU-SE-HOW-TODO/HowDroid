package com.example.howdroid.di

import com.example.howdroid.data.repository.AuthRepositoryImpl
import com.example.howdroid.data.repository.HomeRepositoryImpl
import com.example.howdroid.domain.repository.AuthRepository
import com.example.howdroid.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository =
        authRepository

    @Provides
    @Singleton
    fun providesHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository =
        homeRepository
}
