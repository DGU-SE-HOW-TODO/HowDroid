package com.example.howdroid.domain.model.home

data class Home(
    val rateOfSuccess: Int,
    val todoCategoryData: List<TodoCategoryData>,
) {
    data class TodoCategoryData(
        val todoCategoryId: Int,
        val todoCategory: String,
        val todoData: List<TodoData>,
    )

    data class TodoData(
        val failtagName: String?,
        val isChecked: Boolean,
        val isDelayed: Boolean,
        val isFixed: Boolean,
        val priority: String,
        val todoId: Int,
        val todoName: String,
    )
}
