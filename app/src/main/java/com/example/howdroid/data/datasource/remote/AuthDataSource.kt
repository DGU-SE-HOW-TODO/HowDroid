package com.example.howdroid.data.datasource.remote

import com.example.howdroid.data.model.request.RequestLoginDto
import com.example.howdroid.data.model.request.RequestSignUpDto
import com.example.howdroid.data.model.response.ResponseEmailDto
import com.example.howdroid.data.model.response.ResponseLoginDto
import com.example.howdroid.data.service.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService,
) {

    suspend fun login(requestLogin: RequestLoginDto): Response<ResponseLoginDto> =
        authService.login(requestLogin)

    suspend fun emailDuplication(email: String): ResponseEmailDto =
        authService.emailDuplication(email)

    suspend fun signUp(requestSignUpDto: RequestSignUpDto) =
        authService.signUp(requestSignUpDto)
}
