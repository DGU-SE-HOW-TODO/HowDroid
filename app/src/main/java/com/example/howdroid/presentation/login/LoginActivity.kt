package com.example.howdroid.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.howdroid.R
import com.example.howdroid.data.datasource.local.HowDroidStorage
import com.example.howdroid.databinding.ActivityLoginBinding
import com.example.howdroid.presentation.home.HomeActivity
import com.example.howdroid.presentation.signup.SignUpActivity
import com.example.howdroid.util.UiState
import com.example.howdroid.util.binding.BindingActivity
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.example.howdroid.util.extension.showSnackbar
import com.example.howdroid.util.extension.showToast
import com.example.howdroid.util.extension.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val logInViewModel by viewModels<LogInViewModel>()
    private var email: String = ""
    private var passWord: String = ""
    private var backPressedTime = ZERO

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= TWO_SECONDS) {
                finish()
            } else {
                backPressedTime = System.currentTimeMillis()
                showToast(getString(R.string.application_terminate))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkAutoLogin()
        setTextChangeListeners()
        moveToSignUp()
        clickLoginBtn()
        observeLogin()
        finishApplication()
    }

    private fun checkAutoLogin() {
        val howDroidStorage = HowDroidStorage(this)
        if (howDroidStorage.isLogin) {
            moveToHome()
        }
    }

    private fun observeLogin() {
        logInViewModel.loginState.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    moveToHome()
                }

                is UiState.Failure -> {
                    showSnackbar(binding.root, getString(R.string.login_fail_message))
                }

                else -> Unit
            }
        }
    }

    private fun clickLoginBtn() {
        binding.btnLogin.setOnSingleClickListener {
            logInViewModel.login(email, passWord)
        }
    }

    private fun moveToSignUp() {
        binding.tvLoginSignup.setOnSingleClickListener {
            startActivity<SignUpActivity>()
        }
    }

    private fun moveToHome() {
        val intent = Intent(this, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        showToast(getString(R.string.login_success))
        startActivity(intent)
    }

    private fun setTextChangeListeners() {
        with(binding) {
            etLoginId.addTextChangedListener { updateLoginInfo() }
            etLoginPw.addTextChangedListener { updateLoginInfo() }
        }
    }

    private fun updateLoginInfo() {
        with(binding) {
            email = etLoginId.text.toString()
            passWord = etLoginPw.text.toString()
        }
    }

    private fun finishApplication() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    companion object {
        const val ZERO = 0L
        const val TWO_SECONDS = 2000
    }
}
