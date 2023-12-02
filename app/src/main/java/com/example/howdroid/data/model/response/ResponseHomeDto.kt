package com.example.howdroid.data.model.response

import com.example.howdroid.domain.model.home.Home
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    @SerialName("rateOfSuccess")
    val rateOfSuccess: Int,
    @SerialName("todoCategoryData")
    val todoCategoryData: List<TodoCategoryData>,
) {
    @Serializable
    data class TodoCategoryData(
        @SerialName("todoCategoryId")
        val todoCategoryId: Int,
        @SerialName("todoCategory")
        val todoCategory: String,
        @SerialName("todoData")
        val todoData: List<TodoData>,
    ) {
        @Serializable
        data class TodoData(
            @SerialName("failtagName")
            val failtagName: String?,
            @SerialName("isChecked")
            val isChecked: Boolean,
            @SerialName("isDelayed")
            val isDelayed: Boolean,
            @SerialName("isFixed")
            val isFixed: Boolean,
            @SerialName("priority")
            val priority: String,
            @SerialName("todoId")
            val todoId: Int,
            @SerialName("todoName")
            val todoName: String,
        )
    }

    fun toHome() = Home(
        rateOfSuccess = rateOfSuccess,
        todoCategoryData = todoCategoryData.map { categoryData ->
            Home.TodoCategoryData(
                todoCategoryId = categoryData.todoCategoryId,
                todoCategory = categoryData.todoCategory,
                todoData = categoryData.todoData.map { todoData ->
                    Home.TodoData(
                        failtagName = todoData.failtagName,
                        isChecked = todoData.isChecked,
                        isDelayed = todoData.isDelayed,
                        isFixed = todoData.isFixed,
                        priority = todoData.priority,
                        todoId = todoData.todoId,
                        todoName = todoData.todoName,
                    )
                },
            )
        },
    )
}
