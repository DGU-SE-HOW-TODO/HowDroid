package com.example.howdroid.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.domain.repository.HomeRepository
import com.example.howdroid.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _homeData = MutableLiveData<UiState<Home>>(UiState.Empty)
    val homeData: LiveData<UiState<Home>> get() = _homeData

    init {
        val today = LocalDate.now()
        getHomeData(today.toString())
    }

    fun getHomeData(selectedDate: String) {
        viewModelScope.launch {
            homeRepository.getHomeData(selectedDate)
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
