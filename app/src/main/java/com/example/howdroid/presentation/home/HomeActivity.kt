package com.example.howdroid.presentation.home

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivityHomeBinding
import com.example.howdroid.util.binding.BindingActivity

class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
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
}
