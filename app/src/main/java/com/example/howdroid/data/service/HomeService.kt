package com.example.howdroid.data.service

import com.example.howdroid.data.model.response.ResponseHomeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    @GET("home/{selectedDate}")
    suspend fun getHomeData(
        @Path("selectedDate") selectedDate: String,
    ): ResponseHomeDto
}
