package com.example.howdroid.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentHomeBinding
import com.example.howdroid.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.homeData.observe(viewLifecycleOwner) { homeData ->
            val adapter = HomeTodoOuterAdapter()
            binding.rvOuterHomeTodoList.adapter = adapter
            adapter.submitList(homeData)
        }

        homeViewModel.getHomeData()
    }
}
