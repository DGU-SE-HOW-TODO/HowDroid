package com.example.howdroid.presentation.login

import android.util.Log
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
                val responseHeader = loginResponse.headers()
                val accessToken = responseHeader[AUTHORIZATION]
                if (loginResponse.code() == 200) {
                    howDroidStorage.accessToken = accessToken.toString()
                    _loginState.value = UiState.Success(true)
                    Log.d("aaa", "${howDroidStorage.accessToken}")
                }
            }.onFailure { throwable ->
                _loginState.value = throwable.message?.let { UiState.Failure(it) }
            }
        }
    }

    fun setAutoLogin() {
        howDroidStorage.isLogin = true
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
    }
}
