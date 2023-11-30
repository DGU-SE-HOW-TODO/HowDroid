package com.example.howdroid.domain.repository

import com.example.howdroid.data.model.request.RequestLoginDto
import com.example.howdroid.data.model.response.ResponseLoginDto
import retrofit2.Response

interface AuthRepository {

    suspend fun login(requestLogin: RequestLoginDto): Result<Response<ResponseLoginDto>>
}
