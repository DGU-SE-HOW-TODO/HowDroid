package com.example.howdroid.domain.repository

import com.example.howdroid.data.model.request.RequestMyFailTag
import com.example.howdroid.data.model.response.ResponseMyFailTag

interface MyFailTagRepository {
    suspend fun setMyFailTag(requestMyFailTag: RequestMyFailTag): Result<Unit>
    suspend fun fetchMyFailTag(selectedDate: String): Result<ResponseMyFailTag>
}
