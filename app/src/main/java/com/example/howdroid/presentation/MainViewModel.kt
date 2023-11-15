package com.example.howdroid.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _priorityType = MutableStateFlow<PriorityType?>(null)
    val priorityType get() = _priorityType.asStateFlow()

    fun setSelectedPriorityType(priorityType: PriorityType) {
        _priorityType.value = priorityType
    }
}