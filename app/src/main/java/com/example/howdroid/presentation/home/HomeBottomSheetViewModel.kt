package com.example.howdroid.presentation.home

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

    fun fixToDo(todoId: Long) {
        viewModelScope.launch {
            toDoRepository.fixToDo(todoId)
        }
    }
}
