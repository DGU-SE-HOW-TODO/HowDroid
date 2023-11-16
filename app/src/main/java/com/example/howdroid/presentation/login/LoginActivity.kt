package com.example.howdroid.presentation.login

import android.os.Bundle
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivityLoginBinding
import com.example.howdroid.presentation.signup.SignUpActivity
import com.example.howdroid.util.binding.BindingActivity
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.example.howdroid.util.extension.startActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moveToSignUp()
    }

    private fun moveToSignUp() {
        binding.tvLoginSignup.setOnSingleClickListener {
            startActivity<SignUpActivity>()
        }
    }
}
