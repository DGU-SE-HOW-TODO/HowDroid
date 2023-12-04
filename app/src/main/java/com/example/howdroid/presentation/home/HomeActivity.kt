package com.example.howdroid.presentation.home

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivityHomeBinding
import com.example.howdroid.presentation.login.LoginActivity.Companion.TWO_SECONDS
import com.example.howdroid.presentation.login.LoginActivity.Companion.ZERO
import com.example.howdroid.util.binding.BindingActivity
import com.example.howdroid.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {

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
        initBottomNavigation()
        finishApplication()
    }

    private fun initBottomNavigation() {
        val bnv = binding.bnvHome

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()
        navController?.let {
            it.setGraph(R.navigation.nav_graph)
            bnv.setupWithNavController(it)
        }
    }

    private fun finishApplication() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}
