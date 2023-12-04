package com.example.howdroid.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeBottomSheetViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository,
) : ViewModel() {

    private val _fixToDo = MutableLiveData(false)
    val fixToDo: LiveData<Boolean> get() = _fixToDo

    fun fixToDo(todoId: Long) {
        viewModelScope.launch {
            toDoRepository.fixToDo(todoId)
                .onSuccess {
                    _fixToDo.value = true
                }
                .onFailure {
                    _fixToDo.value = false
                }
        }
    }
}
