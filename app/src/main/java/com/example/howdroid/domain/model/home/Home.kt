package com.example.howdroid.domain.model.home

data class Home(
    val todoCategoryData: List<TodoCategory>,
    val todayDate: String,
    val selectedDate: String,
) {
    data class TodoCategory(
        val todoCategoryId: Int,
        val todoCategory: String,
        val todoData: List<TodoItem>,
    )

    data class TodoItem(
        val todoId: Int,
        val todoCategory: String,
        val todo: String,
        val priority: String,
        val isChecked: Boolean,
        val isFixed: Boolean,
        val failTag: String?,
    )
}
