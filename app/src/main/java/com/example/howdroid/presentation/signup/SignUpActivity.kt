package com.example.howdroid.presentation.signup

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivitySignupBinding
import com.example.howdroid.presentation.login.LoginActivity
import com.example.howdroid.util.UiState
import com.example.howdroid.util.binding.BindingActivity
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.example.howdroid.util.extension.setVisible
import com.example.howdroid.util.extension.showSnackbar
import com.example.howdroid.util.extension.showToast
import com.example.howdroid.util.extension.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    private val signUpViewModel by viewModels<SignUpViewModel>()
    private val emailTextView by lazy { binding.tvSignupEmailMessage }
    private val nickNameTextView by lazy { binding.tvSignupNicknameMessage }
    private val passwordTextView by lazy { binding.tvSignupPasswordMessage }
    private val passwordCheckTextView by lazy { binding.tvSignupPasswordCheckMessage }
    private var email: String = ""
    private var nickName: String = ""
    private var password: String = ""
    private var passwordCheck: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTextChangeListeners()
        observeLiveData()
        checkEmailDuplication()
        observeEmailDuplication()
        clickSignUpButton()
        observeSignUp()
    }

    private fun observeSignUp() {
        signUpViewModel.isSignUp.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    showToast(getString(R.string.signup_success))
                    startActivity<LoginActivity>()
                }

                is UiState.Failure -> {
                    showSnackbar(binding.root, getString(R.string.signup_fail))
                }

                else -> Unit
            }
        }
    }

    private fun clickSignUpButton() {
        binding.btnSignup.setOnSingleClickListener {
            if (signUpViewModel.isButtonEnabled.value == true) {
                signUpViewModel.signup(email, nickName, password)
            }
        }
    }

    private fun observeEmailDuplication() {
        signUpViewModel.emailValid.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    if (uiState.data) {
                        binding.tvSignupEmailDuplicationSuccess.setVisible(VISIBLE)
                        binding.tvSignupEmailDuplication.setVisible(INVISIBLE)
                    } else {
                        binding.tvSignupEmailDuplicationSuccess.setVisible(INVISIBLE)
                        binding.tvSignupEmailDuplication.setVisible(VISIBLE)
                    }
                }

                is UiState.Failure -> {
                    showSnackbar(binding.root, "서버 연결 실패")
                }

                else -> Unit
            }
        }
    }

    private fun checkEmailDuplication() {
        binding.tvSignupEmailDoubleCheck.setOnSingleClickListener {
            if (signUpViewModel.emailData.value is UiState.Success) {
                signUpViewModel.emailDuplication(email)
            }
        }
    }

    private fun observeLiveData() {
        signUpViewModel.emailData.observe(this) { uiState ->
            handleUiState(uiState, emailTextView)
            if (uiState !is UiState.Success) {
                binding.tvSignupEmailDuplicationSuccess.setVisible(INVISIBLE)
                binding.tvSignupEmailDuplication.setVisible(INVISIBLE)
            }
        }
        signUpViewModel.nickNameData.observe(this) { handleUiState(it, nickNameTextView) }
        signUpViewModel.passwordData.observe(this) { handleUiState(it, passwordTextView) }
        signUpViewModel.passwordCheckData.observe(this) { handleUiState(it, passwordCheckTextView) }
        signUpViewModel.isButtonEnabled.observe(this) { binding.btnSignup.isEnabled = it }
    }

    private fun setTextChangeListeners() {
        with(binding) {
            etSignupEmail.addTextChangedListener { updateSignUpInfo() }
            etSignupNickname.addTextChangedListener { updateSignUpInfo() }
            etSignupPassword.addTextChangedListener { updateSignUpInfo() }
            etSignupPasswordCheck.addTextChangedListener { updateSignUpInfo() }
        }
    }

    private fun handleUiState(uiState: UiState<String>, textView: TextView) {
        with(textView) {
            when (uiState) {
                is UiState.Success -> setVisible(INVISIBLE)
                is UiState.Failure -> setVisible(VISIBLE)
                is UiState.Empty -> setVisible(INVISIBLE)
                else -> Unit
            }
        }
    }

    private fun updateSignUpInfo() {
        with(binding) {
            email = etSignupEmail.text.toString()
            nickName = etSignupNickname.text.toString()
            password = etSignupPassword.text.toString()
            passwordCheck = etSignupPasswordCheck.text.toString()

            signUpViewModel.setSignUpState(email, nickName, password, passwordCheck)
        }
    }
}
