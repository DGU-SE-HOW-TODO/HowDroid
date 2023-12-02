package com.example.howdroid.domain.repository

import com.example.howdroid.data.model.request.RequestMyFailTag

interface MyFailTagRepository {
    suspend fun setMyFailTag(requestMyFailTag: RequestMyFailTag): Result<Unit>
}
