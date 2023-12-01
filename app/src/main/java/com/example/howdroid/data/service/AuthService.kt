package com.example.howdroid.data.service

import com.example.howdroid.data.model.request.RequestLoginDto
import com.example.howdroid.data.model.response.ResponseEmailDto
import com.example.howdroid.data.model.response.ResponseLoginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("member/login")
    suspend fun login(
        @Body requestLogin: RequestLoginDto,
    ): Response<ResponseLoginDto>

    @GET("member/{email}")
    suspend fun emailDuplication(
        @Path("email") email: String,
    ): ResponseEmailDto
}
