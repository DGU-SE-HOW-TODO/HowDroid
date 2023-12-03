package com.example.howdroid.domain.repository

import com.example.howdroid.data.model.request.RequestAddToDoDto

interface ToDoRepository {

    suspend fun checkToDo(toDoId: Long): Result<Unit>

    suspend fun addToDo(requestAddToDoDto: RequestAddToDoDto): Result<Unit>
}
