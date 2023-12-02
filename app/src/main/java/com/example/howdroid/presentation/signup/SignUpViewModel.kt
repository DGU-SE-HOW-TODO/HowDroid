package com.example.howdroid.presentation.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.data.model.request.RequestSignUpDto
import com.example.howdroid.domain.repository.AuthRepository
import com.example.howdroid.util.UiState
import com.example.howdroid.util.extension.addSourceList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _emailData = MutableLiveData<UiState<String>>(UiState.Empty)
    val emailData: LiveData<UiState<String>> = _emailData

    private val _nickNameData = MutableLiveData<UiState<String>>(UiState.Empty)
    val nickNameData: LiveData<UiState<String>> = _nickNameData

    private val _passwordData = MutableLiveData<UiState<String>>(UiState.Empty)
    val passwordData: LiveData<UiState<String>> = _passwordData

    private val _passwordCheckData = MutableLiveData<UiState<String>>(UiState.Empty)
    val passwordCheckData: LiveData<UiState<String>> = _passwordCheckData

    private val _emailValid = MutableLiveData<UiState<Boolean>>(UiState.Empty)
    val emailValid: LiveData<UiState<Boolean>> get() = _emailValid

    private val _isSignUp = MutableLiveData<UiState<Boolean>>(UiState.Empty)
    val isSignUp: LiveData<UiState<Boolean>> get() = _isSignUp

    val isButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSourceList(
            _emailData,
            _nickNameData,
            _passwordData,
            _passwordCheckData,
            _emailValid,
        ) { checkButtonEnabled() }
    }

    fun emailDuplication(email: String) {
        viewModelScope.launch {
            authRepository.emailDuplication(email)
                .onSuccess {
                    _emailValid.value = UiState.Success(true)
                }
                .onFailure { throwable ->
                    _emailValid.value = throwable.message?.let { UiState.Failure(it) }
                }
        }
    }

    fun signup(email: String, nickName: String, password: String) {
        viewModelScope.launch {
            authRepository.signUp(
                RequestSignUpDto(
                    email,
                    nickName,
                    password,
                ),
            ).onSuccess {
                _isSignUp.value = UiState.Success(true)
            }.onFailure { throwable ->
                _isSignUp.value = throwable.message?.let { UiState.Failure(it) }
            }
        }
    }

    fun setSignUpState(
        email: String,
        nickName: String,
        password: String,
        passwordCheck: String,
    ) {
        _emailData.value = validateField(email) {
            Patterns.EMAIL_ADDRESS.matcher(it).matches()
        }

        _nickNameData.value = validateField(nickName) {
            it.length in 2..6
        }

        _passwordData.value = validateField(password) {
            it.matches(Regex(PASSWORD_REGEX))
        }

        _passwordCheckData.value = validateField(passwordCheck) {
            it == password
        }
    }

    private fun validateField(value: String, validation: (String) -> Boolean): UiState<String> {
        return if (validation(value)) {
            UiState.Success(value)
        } else if (value.isEmpty()) {
            UiState.Empty
        } else {
            UiState.Failure(INVALID_STRING)
        }
    }

    private fun checkButtonEnabled(): Boolean {
        val isEmailSuccess = _emailData.value is UiState.Success
        val isNickNameSuccess = _nickNameData.value is UiState.Success
        val isPasswordSuccess = _passwordData.value is UiState.Success
        val isPasswordCheckSuccess = _passwordCheckData.value is UiState.Success
        val isEmailDuplicateSuccess = _emailValid.value is UiState.Success

        return isEmailSuccess && isNickNameSuccess && isPasswordSuccess && isPasswordCheckSuccess && isEmailDuplicateSuccess
    }

    companion object {
        private const val PASSWORD_REGEX = ".*[a-zA-Z]+.*[0-9]+.*"
        const val INVALID_STRING = "invalid"
    }
}
