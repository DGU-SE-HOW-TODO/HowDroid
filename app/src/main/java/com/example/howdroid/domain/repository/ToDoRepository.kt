package com.example.howdroid.domain.repository

interface ToDoRepository {

    suspend fun checkToDo(toDoId: Long): Result<Unit>
}
