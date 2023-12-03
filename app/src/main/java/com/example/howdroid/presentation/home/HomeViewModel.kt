package com.example.howdroid.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.data.model.request.RequestCategoryDto
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.domain.repository.HomeRepository
import com.example.howdroid.domain.repository.ToDoRepository
import com.example.howdroid.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val toDoRepository: ToDoRepository,
) : ViewModel() {

    private val _homeData = MutableLiveData<UiState<Home>>(UiState.Empty)
    val homeData: LiveData<UiState<Home>> get() = _homeData
    private val _selectedDate = MutableLiveData<String>(LocalDate.now().toString())
    val selectedDate get() = _selectedDate

    init {
        getHomeData()
    }

    fun getHomeData() {
        viewModelScope.launch {
            _selectedDate.value?.let {
                homeRepository.getHomeData(it)
                    .onSuccess {
                        _homeData.value = UiState.Success(it)
                    }.onFailure { throwable ->
                        _homeData.value = throwable.message?.let {
                            UiState.Failure(it)
                        }
                    }
            }
        }
    }

    fun postCategory(categoryName: String) {
        viewModelScope.launch {
            homeRepository.postCategory(
                RequestCategoryDto(
                    categoryName,
                ),
            )
        }
    }

    fun setSelectedDate(selectedDate: String) {
        _selectedDate.value = selectedDate
    }

    fun checkTodo(todoId: Long) {
        viewModelScope.launch {
            toDoRepository.checkToDo(todoId)
        }
    }
}
