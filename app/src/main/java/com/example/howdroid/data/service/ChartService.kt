package com.example.howdroid.data.service

import com.example.howdroid.data.model.response.ResponseStatistic
import retrofit2.http.GET
import retrofit2.http.Path

interface ChartService {
    @GET("statistic/{selectedDate}")
    suspend fun fetchStatistic(
        @Path("selectedDate") selectedDate: String,
    ): ResponseStatistic
}
