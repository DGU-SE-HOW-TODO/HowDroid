package com.example.howdroid.data.datasource.remote

import com.example.howdroid.data.model.request.RequestMyFailTag
import com.example.howdroid.data.service.MyFailTagService
import javax.inject.Inject

class MyFailTagDataSource @Inject constructor(
    private val myFailTagService: MyFailTagService
) {
    suspend fun setMyFailTag(requestMyFailTag: RequestMyFailTag) =
        myFailTagService.setMyFailTag(requestMyFailTag)
}
