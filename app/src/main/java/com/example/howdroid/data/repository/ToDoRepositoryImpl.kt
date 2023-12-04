package com.example.howdroid.data.repository

import com.example.howdroid.data.datasource.remote.ToDoDataSource
import com.example.howdroid.data.model.request.RequestAddToDoDto
import com.example.howdroid.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoDataSource: ToDoDataSource,
) : ToDoRepository {
    override suspend fun checkToDo(toDoId: Long): Result<Unit> =
        runCatching { toDoDataSource.checkToDo(toDoId) }

    override suspend fun addToDo(requestAddToDoDto: RequestAddToDoDto): Result<Unit> =
        runCatching { toDoDataSource.addToDo(requestAddToDoDto) }

    override suspend fun fixToDo(toDoId: Long): Result<Unit> =
        runCatching { toDoDataSource.fixToDo(toDoId) }
}
