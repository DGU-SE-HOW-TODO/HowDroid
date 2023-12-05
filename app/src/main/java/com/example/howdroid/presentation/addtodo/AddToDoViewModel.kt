package com.example.howdroid.presentation.addtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.data.model.request.RequestAddToDoDto
import com.example.howdroid.domain.repository.ToDoRepository
import com.example.howdroid.presentation.signup.SignUpViewModel
import com.example.howdroid.presentation.type.PriorityType
import com.example.howdroid.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository,
) : ViewModel() {
    private val _priorityType = MutableStateFlow<PriorityType?>(null)
    val priorityType get() = _priorityType.asStateFlow()

    private val _toDoTitle = MutableLiveData<UiState<String>>(UiState.Empty)
    val toDoTitle: LiveData<UiState<String>> = _toDoTitle

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    fun addTodo(selectedDate: String, todo: String, todoCategoryId: Int) {
        viewModelScope.launch {
            toDoRepository.addToDo(
                RequestAddToDoDto(
                    _priorityType.value?.typeString.toString(),
                    selectedDate,
                    todo,
                    todoCategoryId,
                ),
            )
        }
    }

    fun setSelectedPriorityType(priorityType: PriorityType) {
        _priorityType.value = priorityType
        checkButtonEnabled()
    }

    fun setAddToDoState(
        toDoTitle: String,
    ) {
        _toDoTitle.value = validateField(toDoTitle) {
            it.length in 1..15
        }
        checkButtonEnabled()
    }

    private fun validateField(value: String, validation: (String) -> Boolean): UiState<String> {
        return if (validation(value)) {
            UiState.Success(value)
        } else if (value.isEmpty()) {
            UiState.Empty
        } else {
            UiState.Failure(SignUpViewModel.INVALID_STRING)
        }
    }

    private fun checkButtonEnabled() {
        val isToDoValid = _toDoTitle.value is UiState.Success
        val isPriorityTypeValid = _priorityType.value != null

        val isButtonEnabled =
            isToDoValid && isPriorityTypeValid

        _isButtonEnabled.value = isButtonEnabled
    }
}
