package com.example.howdroid.domain.repository

import com.example.howdroid.data.model.request.RequestLoginDto
import com.example.howdroid.data.model.request.RequestSignUpDto
import com.example.howdroid.data.model.response.ResponseEmailDto
import com.example.howdroid.data.model.response.ResponseLoginDto

interface AuthRepository {

    suspend fun login(requestLogin: RequestLoginDto): Result<ResponseLoginDto>

    suspend fun emailDuplication(email: String): Result<ResponseEmailDto>

    suspend fun signUp(requestSignUpDto: RequestSignUpDto): Result<Unit>
}
