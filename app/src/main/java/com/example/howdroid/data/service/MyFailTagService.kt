package com.example.howdroid.data.service

import com.example.howdroid.data.model.request.RequestMyFailTag
import retrofit2.http.Body
import retrofit2.http.POST

interface MyFailTagService {
    @POST("failtag/todoCategory")
    suspend fun setMyFailTag(
        @Body requestMyFailTag: RequestMyFailTag,
    ): Unit
}
