package com.example.howdroid.data.service

import com.example.howdroid.data.model.request.RequestMyFailTag
import com.example.howdroid.data.model.response.ResponseMyFailTag
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyFailTagService {
    @POST("failtag/todoCategory")
    suspend fun setMyFailTag(
        @Body requestMyFailTag: RequestMyFailTag,
    )

    @GET("/failtag/{selectedDate}")
    suspend fun fetchMyFailTag(
        @Path("selectedDate") selectedDate: String
    ): ResponseMyFailTag
}
