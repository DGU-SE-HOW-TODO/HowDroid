package com.example.howdroid.data.datasource.remote

import com.example.howdroid.data.model.response.ResponseFeedBack
import com.example.howdroid.data.model.response.ResponseStatistic
import com.example.howdroid.data.service.ChartService
import javax.inject.Inject

class ChartDataSource @Inject constructor(
    private val chartService: ChartService
) {
    suspend fun fetchStatistic(selectedDate: String): ResponseStatistic =
        chartService.fetchStatistic(selectedDate)

    suspend fun fetchFeedBack(selectedDate: String): ResponseFeedBack =
        chartService.fetchFeedBack(selectedDate)
}
