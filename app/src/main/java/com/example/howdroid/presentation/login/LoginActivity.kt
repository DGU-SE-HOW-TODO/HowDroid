package com.example.howdroid.presentation.login

import android.os.Bundle
import android.util.Log
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
import com.example.howdroid.util.extension.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val logInViewModel by viewModels<LogInViewModel>()

    private var email: String = ""
    private var passWord: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkAutoLogin()
        setTextChangeListeners()
        moveToSignUp()
        clickLoginBtn()
        observeLogin()
    }

    private fun checkAutoLogin() {
        val howDroidStorage = HowDroidStorage(this)
        if (howDroidStorage.isLogin) {
            startActivity<HomeActivity>()
            // howDroidStorage.clear()
            Log.d("aaa", "${howDroidStorage.accessToken}")
        }
    }

    private fun observeLogin() {
        logInViewModel.loginState.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    setAutoLogin()
                    startActivity<HomeActivity>()
                }

                is UiState.Failure -> {
                    showSnackbar(binding.root, getString(R.string.login_fail_message))
                }

                else -> Unit
            }
        }
    }

    private fun setAutoLogin() {
        if (binding.cbLoginAuto.isChecked) {
            logInViewModel.setAutoLogin()
        }
    }

    private fun clickLoginBtn() {
        binding.btnLogin.setOnSingleClickListener {
            // 나중에 email, passWord 로 바꾸기
            logInViewModel.login("helloworld@naver.com", "helloworldps")
        }
    }

    private fun moveToSignUp() {
        binding.tvLoginSignup.setOnSingleClickListener {
            startActivity<SignUpActivity>()
        }
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
}
