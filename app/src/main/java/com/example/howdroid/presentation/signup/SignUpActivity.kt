package com.example.howdroid.presentation.signup

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivitySignupBinding
import com.example.howdroid.util.UiState
import com.example.howdroid.util.binding.BindingActivity
import com.example.howdroid.util.extension.setVisible

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
    }

    private fun observeLiveData() {
        signUpViewModel.emailData.observe(this) { handleUiState(it, emailTextView) }
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
