package com.example.howdroid.presentation.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.howdroid.util.UiState

class SignUpViewModel : ViewModel() {

    private val _emailData = MutableLiveData<UiState<String>>(UiState.Empty)
    val emailData: LiveData<UiState<String>> = _emailData

    private val _nickNameData = MutableLiveData<UiState<String>>(UiState.Empty)
    val nickNameData: LiveData<UiState<String>> = _nickNameData

    private val _passwordData = MutableLiveData<UiState<String>>(UiState.Empty)
    val passwordData: LiveData<UiState<String>> = _passwordData

    private val _passwordCheckData = MutableLiveData<UiState<String>>(UiState.Empty)
    val passwordCheckData: LiveData<UiState<String>> = _passwordCheckData

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    fun setSignUpLiveData(
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

        checkButtonEnabled()
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

    private fun checkButtonEnabled() {
        val isEmailSuccess = _emailData.value is UiState.Success
        val isNickNameSuccess = _nickNameData.value is UiState.Success
        val isPasswordSuccess = _passwordData.value is UiState.Success
        val isPasswordCheckSuccess = _passwordCheckData.value is UiState.Success

        val isButtonEnabled =
            isEmailSuccess && isNickNameSuccess && isPasswordSuccess && isPasswordCheckSuccess

        _isButtonEnabled.value = isButtonEnabled
    }

    companion object {
        private const val PASSWORD_REGEX = ".*[a-zA-Z]+.*[0-9]+.*"
        private const val INVALID_STRING = "invalid"
    }
}
