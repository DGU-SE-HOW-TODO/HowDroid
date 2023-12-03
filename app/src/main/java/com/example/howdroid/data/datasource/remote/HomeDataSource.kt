package com.example.howdroid.data.datasource.remote

import com.example.howdroid.data.model.request.RequestPutFailTag
import com.example.howdroid.data.model.request.RequestCategoryDto
import com.example.howdroid.data.model.response.ResponseHomeDto
import com.example.howdroid.data.service.HomeService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val homeService: HomeService,
) {
    suspend fun getHomeData(selectedDate: String): ResponseHomeDto =
        homeService.getHomeData(selectedDate)

    // TODO TODO로 옮기기
    suspend fun putFailTag(toDoId: Int, requestPutFailTag: RequestPutFailTag) =
        homeService.putFailTag(toDoId, requestPutFailTag)

    suspend fun postCategory(requestCategoryDto: RequestCategoryDto) =
        homeService.postCategory(requestCategoryDto)
}
