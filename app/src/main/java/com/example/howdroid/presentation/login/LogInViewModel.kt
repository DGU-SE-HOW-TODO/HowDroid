package com.example.howdroid.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.data.datasource.local.HowDroidStorage
import com.example.howdroid.data.model.request.RequestLoginDto
import com.example.howdroid.domain.repository.AuthRepository
import com.example.howdroid.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val howDroidStorage: HowDroidStorage,
) : ViewModel() {

    private val _loginState = MutableLiveData<UiState<Boolean>>(UiState.Loading)
    val loginState: LiveData<UiState<Boolean>> get() = _loginState

    fun login(loginEmail: String, loginPassword: String) {
        viewModelScope.launch {
            authRepository.login(
                RequestLoginDto(
                    loginEmail,
                    loginPassword,
                ),
            ).onSuccess { loginResponse ->
                howDroidStorage.accessToken = loginResponse.accessToken
                howDroidStorage.isLogin = true
                _loginState.value = UiState.Success(true)
            }.onFailure { throwable ->
                _loginState.value = throwable.message?.let { UiState.Failure(it) }
            }
        }
    }
}
