package com.example.howdroid.di

import com.example.howdroid.data.service.AuthService
import com.example.howdroid.data.service.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create()

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService = retrofit.create()
}
