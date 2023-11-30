package com.example.howdroid.data.repository

import com.example.howdroid.data.datasource.remote.AuthDataSource
import com.example.howdroid.data.model.request.RequestLoginDto
import com.example.howdroid.data.model.response.ResponseLoginDto
import com.example.howdroid.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    override suspend fun login(requestLogin: RequestLoginDto): Result<Response<ResponseLoginDto>> =
        runCatching { authDataSource.login(requestLogin) }
}
