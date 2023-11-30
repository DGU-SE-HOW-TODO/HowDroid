package com.example.howdroid.data.datasource.remote

import com.example.howdroid.data.model.request.RequestLoginDto
import com.example.howdroid.data.model.response.ResponseLoginDto
import com.example.howdroid.data.service.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService,
) {

    suspend fun login(requestLogin: RequestLoginDto): Response<ResponseLoginDto> =
        authService.login(requestLogin)
}
