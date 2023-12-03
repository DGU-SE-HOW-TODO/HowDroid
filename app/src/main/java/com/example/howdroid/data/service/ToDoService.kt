package com.example.howdroid.data.service

import retrofit2.http.PATCH
import retrofit2.http.Path

interface ToDoService {
    @PATCH("todo/check/{todoId}")
    suspend fun checkToDo(
        @Path("todoId") todoId: Long,
    )
}
