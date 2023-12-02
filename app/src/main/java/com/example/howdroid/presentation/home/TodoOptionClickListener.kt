package com.example.howdroid.presentation.home

import com.example.howdroid.domain.model.home.Home

interface TodoOptionClickListener {
    fun onOptionClick(todoItem: Home.TodoData)
}
