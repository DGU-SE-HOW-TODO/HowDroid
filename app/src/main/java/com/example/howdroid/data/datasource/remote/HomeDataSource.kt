package com.example.howdroid.data.datasource.remote

import com.example.howdroid.data.model.response.ResponseHomeDto
import com.example.howdroid.data.service.HomeService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val homeService: HomeService,
) {

    suspend fun getHomeData(selectedDate: String): ResponseHomeDto =
        homeService.getHomeData(selectedDate)
}
