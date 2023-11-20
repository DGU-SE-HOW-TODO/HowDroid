package com.example.howdroid.presentation.home

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentHomeBinding
import com.example.howdroid.util.binding.BindingFragment
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.example.howdroid.util.extension.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHome()
        setHomeTitle()
        addCategory()
    }

    private fun addCategory() {
        with(binding) {
            tvHomeAddCategoty.setOnSingleClickListener {
                clHomeAddCategory.setVisible(VISIBLE)
            }
        }
    }

    private fun setHome() {
        homeViewModel.homeData.observe(viewLifecycleOwner) { homeData ->
            val adapter = HomeTodoOuterAdapter()
            binding.rvOuterHomeTodoList.adapter = adapter
            adapter.submitList(homeData)
        }

        homeViewModel.getHomeData()
    }

    private fun setHomeTitle() {
        val homeTitleString = getString(R.string.home_title)
        val toDoPercent = 70 // 나중에 서버에서 받는 값으로 바꾸기
        val homeTitle = homeTitleString.replace("%d%", "$toDoPercent%")
        val postHomeTitle = SpannableString(homeTitle)
        val startIndex = homeTitle.indexOf(toDoPercent.toString())
        val endIndex = startIndex + toDoPercent.toString().length + 1

        postHomeTitle.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.Green_300)),
            startIndex,
            endIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
        binding.tvToolbarTitle.text = postHomeTitle
    }
}
