package com.example.howdroid.data.datasource.local

import com.example.howdroid.domain.model.home.Home

class HomeMockData {
    val mockHomeData = Home(
        todoCategoryData = listOf(
            Home.TodoCategory(
                todoCategoryId = 1,
                todoCategory = "Study",
                todoData = listOf(
                    Home.TodoItem(
                        todoId = 1,
                        todoCategory = "Study",
                        todo = "Submit Compiler Implementation Assignment",
                        priority = "매우중요",
                        isChecked = true,
                        isFixed = true,
                        failTag = null,
                    ),
                    Home.TodoItem(
                        todoId = 2,
                        todoCategory = "Study",
                        todo = "Study for Software Engineering",
                        priority = "중요",
                        isChecked = false,
                        isFixed = false,
                        failTag = null,
                    ),
                ),
            ),
            Home.TodoCategory(
                todoCategoryId = 2,
                todoCategory = "Exercise",
                todoData = listOf(
                    Home.TodoItem(
                        todoId = 3,
                        todoCategory = "Exercise",
                        todo = "Run for 1 hour",
                        priority = "중요하지 않음",
                        isChecked = false,
                        isFixed = false,
                        failTag = "Sleep",
                    ),
                ),
            ),
        ),
        todayDate = "2023-11-10",
        selectedDate = "2023-11-02",
    )
}
