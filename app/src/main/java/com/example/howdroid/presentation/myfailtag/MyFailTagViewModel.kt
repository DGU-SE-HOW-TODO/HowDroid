package com.example.howdroid.presentation.myfailtag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.data.model.request.RequestMyFailTag
import com.example.howdroid.domain.repository.MyFailTagRepository
import com.example.howdroid.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MyFailTagViewModel @Inject constructor(
    private val myFailTagRepository: MyFailTagRepository
) : ViewModel() {
    private val _isSetFailTagSuccess = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val isSetFailTagSuccess get() = _isSetFailTagSuccess.asStateFlow()
    fun setMyFailTag(failTagList: MutableList<String>) {
        viewModelScope.launch {
            myFailTagRepository.setMyFailTag(
                RequestMyFailTag(
                    selectedDate = LocalDate.now().toString(),
                    selectedFailtagList = failTagList
                )
            ).onSuccess {
                _isSetFailTagSuccess.value = UiState.Success(true)
            }.onFailure { throwable ->
                Timber.e(throwable.message)
            }
        }
    }
}
