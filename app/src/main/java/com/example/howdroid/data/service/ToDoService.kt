package com.example.howdroid.data.service

import com.example.howdroid.data.model.request.RequestAddToDoDto
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ToDoService {
    @PATCH("todo/check/{todoId}")
    suspend fun checkToDo(
        @Path("todoId") todoId: Long,
    )

    @POST("todo/assign")
    suspend fun addToDo(
        @Body requestAddToDoDto: RequestAddToDoDto,
    )

    @PATCH("todo/fix/{todoId}")
    suspend fun fixToDo(
        @Path("todoId") todoId: Long,
    )
}
