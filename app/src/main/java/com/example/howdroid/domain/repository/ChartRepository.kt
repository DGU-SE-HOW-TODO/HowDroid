package com.example.howdroid.domain.repository

import com.example.howdroid.data.model.response.ResponseFeedBack
import com.example.howdroid.data.model.response.ResponseStatistic

interface ChartRepository {
    suspend fun fetchStatistic(selectedDate: String): Result<ResponseStatistic>
    suspend fun fetchFeedBack(selectedDate: String): Result<ResponseFeedBack>
}
