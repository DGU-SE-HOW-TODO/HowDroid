package com.example.howdroid.domain.repository

import com.example.howdroid.data.model.request.RequestLoginDto
import com.example.howdroid.data.model.request.RequestSignUpDto
import com.example.howdroid.data.model.response.ResponseEmailDto
import com.example.howdroid.data.model.response.ResponseLoginDto
import retrofit2.Response

interface AuthRepository {

    suspend fun login(requestLogin: RequestLoginDto): Result<ResponseLoginDto>

    suspend fun emailDuplication(email: String): Result<Response<ResponseEmailDto>>

    suspend fun signUp(requestSignUpDto: RequestSignUpDto): Result<Unit>
}
