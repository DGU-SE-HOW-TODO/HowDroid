package com.example.howdroid.domain.repository

import com.example.howdroid.data.model.request.RequestPutFailTag
import com.example.howdroid.data.model.request.RequestCategoryDto
import com.example.howdroid.domain.model.home.Home

interface HomeRepository {

    suspend fun getHomeData(selectedDate: String): Result<Home>
    suspend fun putFailTag(toDoId: Int, requestPutFailTag: RequestPutFailTag): Result<Unit>
    suspend fun postCategory(requestCategoryDto: RequestCategoryDto): Result<Unit>
}
