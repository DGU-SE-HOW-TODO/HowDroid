package com.example.howdroid.data.service

import com.example.howdroid.data.model.request.RequestCategoryDto
import com.example.howdroid.data.model.response.ResponseHomeDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeService {

    @GET("home/{selectedDate}")
    suspend fun getHomeData(
        @Path("selectedDate") selectedDate: String,
    ): ResponseHomeDto

    @POST("category")
    suspend fun postCategory(
        @Body requestCategoryDto: RequestCategoryDto,
    )
}
