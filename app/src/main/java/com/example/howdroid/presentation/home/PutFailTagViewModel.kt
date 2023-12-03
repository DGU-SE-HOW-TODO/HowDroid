package com.example.howdroid.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.domain.repository.MyFailTagRepository
import com.example.howdroid.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PutFailTagViewModel @Inject constructor(
    private val myFailTagRepository: MyFailTagRepository
) : ViewModel() {
    private val _myFailTagList = MutableStateFlow<UiState<MutableList<String>>>(UiState.Loading)
    val myFailTagList get() = _myFailTagList.asStateFlow()
    private val _selectedFailTag = MutableStateFlow<String?>(null)
    val selectedFailTag get() = _selectedFailTag.asStateFlow()
    private val _isDelayedTomorrow = MutableStateFlow<Boolean>(false)
    val isDelayedTomorrow get() = _isDelayedTomorrow.asStateFlow()

    fun fetchMyFailTagList(selectedDate: String) {
        viewModelScope.launch {
            myFailTagRepository.fetchMyFailTag(selectedDate)
                .onSuccess { myFailTagList ->
                    _myFailTagList.value = UiState.Success(myFailTagList.failtags.toMutableList())
                }
                .onFailure { throwable ->
                    Timber.d(throwable.message)
                }
        }
    }

    fun setSelectedFailTag(failTag: String?) {
        _selectedFailTag.value = failTag
    }

    fun setIsDelayedTomorrow() {
        _isDelayedTomorrow.value = !_isDelayedTomorrow.value
    }
}
