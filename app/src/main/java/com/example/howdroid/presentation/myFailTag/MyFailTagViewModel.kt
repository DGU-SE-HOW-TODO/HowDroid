package com.example.howdroid.presentation.myFailTag

import androidx.lifecycle.ViewModel
import com.example.howdroid.presentation.type.FailTagType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyFailTagViewModel : ViewModel() {
    private val _failTagList = MutableStateFlow<List<FailTagType>>(listOf())
    val failTagList get() = _failTagList.asStateFlow()
    private val _failTagListSize = MutableStateFlow<Int>(0)
    val failTagListSize get() = _failTagListSize.asStateFlow()

    fun setFailTagListSize(size: Int) {
        _failTagListSize.value = size
    }
}
