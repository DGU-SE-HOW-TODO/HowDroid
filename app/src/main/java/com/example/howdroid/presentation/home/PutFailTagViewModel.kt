package com.example.howdroid.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PutFailTagViewModel @Inject constructor() : ViewModel() {
    private val _selectedFailTag = MutableStateFlow<String?>(null)
    val selectedFailTag get() = _selectedFailTag.asStateFlow()
    private val _isDelayedTomorrow = MutableStateFlow<Boolean>(false)
    val isDelayedTomorrow get() = _isDelayedTomorrow.asStateFlow()

    fun setSelectedFailTag(failTag: String?) {
        _selectedFailTag.value = failTag
    }

    fun setIsDelayedTomorrow() {
        _isDelayedTomorrow.value = !_isDelayedTomorrow.value
    }
}
