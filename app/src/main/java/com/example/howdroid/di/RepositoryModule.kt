package com.example.howdroid.di

import com.example.howdroid.data.repository.AuthRepositoryImpl
import com.example.howdroid.data.repository.ChartRepositoryImpl
import com.example.howdroid.data.repository.HomeRepositoryImpl
import com.example.howdroid.data.repository.MyFailTagRepositoryImpl
import com.example.howdroid.data.repository.ToDoRepositoryImpl
import com.example.howdroid.domain.repository.AuthRepository
import com.example.howdroid.domain.repository.ChartRepository
import com.example.howdroid.domain.repository.HomeRepository
import com.example.howdroid.domain.repository.MyFailTagRepository
import com.example.howdroid.domain.repository.ToDoRepository
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

    @Provides
    @Singleton
    fun providesMyFailTagRepository(myFailTagRepositoryImpl: MyFailTagRepositoryImpl): MyFailTagRepository =
        myFailTagRepositoryImpl

    @Provides
    @Singleton
    fun providesToDoRepository(toDoRepositoryImpl: ToDoRepositoryImpl): ToDoRepository =
        toDoRepositoryImpl

    @Provides
    @Singleton
    fun providesChartRepository(chartRepositoryImpl: ChartRepositoryImpl): ChartRepository =
        chartRepositoryImpl
}
