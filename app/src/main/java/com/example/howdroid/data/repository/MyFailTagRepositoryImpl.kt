package com.example.howdroid.data.repository

import com.example.howdroid.data.datasource.remote.MyFailTagDataSource
import com.example.howdroid.data.model.request.RequestMyFailTag
import com.example.howdroid.domain.repository.MyFailTagRepository
import javax.inject.Inject

class MyFailTagRepositoryImpl @Inject constructor(
    private val myFailTagDataSource: MyFailTagDataSource
) : MyFailTagRepository {
    override suspend fun setMyFailTag(requestMyFailTag: RequestMyFailTag): Result<Unit> =
        runCatching {
            myFailTagDataSource.setMyFailTag(requestMyFailTag)
        }
}
