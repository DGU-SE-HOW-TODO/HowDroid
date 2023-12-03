package com.example.howdroid.data.datasource.remote

import com.example.howdroid.data.service.ToDoService
import javax.inject.Inject

class ToDoDataSource @Inject constructor(
    private val toDoService: ToDoService,
) {
    suspend fun checkToDo(toDoId: Long) =
        toDoService.checkToDo(toDoId)
}
