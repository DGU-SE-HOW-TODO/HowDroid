package com.example.howdroid.presentation.home

import com.example.howdroid.domain.model.home.Home

interface TodoCheckClickListener {
    fun onCheckClick(todoItem: Home.TodoData)
}
