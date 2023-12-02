package com.example.howdroid.data.repository

import com.example.howdroid.data.datasource.remote.HomeDataSource
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {

    override suspend fun getHomeData(selectedDate: String): Result<Home> =
        runCatching { homeDataSource.getHomeData(selectedDate).toHome() }
}
