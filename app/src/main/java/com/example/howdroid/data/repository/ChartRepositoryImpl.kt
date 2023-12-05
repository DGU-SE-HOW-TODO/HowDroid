package com.example.howdroid.data.repository

import com.example.howdroid.data.datasource.remote.ChartDataSource
import com.example.howdroid.data.model.response.ResponseFeedBack
import com.example.howdroid.data.model.response.ResponseStatistic
import com.example.howdroid.domain.repository.ChartRepository
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(
    private val chartDataSource: ChartDataSource
) : ChartRepository {
    override suspend fun fetchStatistic(selectedDate: String): Result<ResponseStatistic> =
        runCatching {
            chartDataSource.fetchStatistic(selectedDate)
        }

    override suspend fun fetchFeedBack(selectedDate: String): Result<ResponseFeedBack> =
        runCatching {
            chartDataSource.fetchFeedBack(selectedDate)
        }
}
