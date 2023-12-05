package com.example.howdroid.presentation.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentChartBinding
import com.example.howdroid.util.binding.BindingFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ChartFragment : BindingFragment<FragmentChartBinding>(R.layout.fragment_chart) {
    private val viewModel: ChartViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setChartPagerAdapter()
        collectData()
    }

    private fun setChartPagerAdapter() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ChartStatisticFragment())
        fragmentList.add(ChartFeedbackFragment())

        val adapter = ChartPagerAdapter(fragmentList, requireActivity())
        binding.vpChart.adapter = adapter

        TabLayoutMediator(binding.tabLayoutChart, binding.vpChart) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.chart_statistic_title)
                1 -> tab.text = getString(R.string.chart_feedback_title)
            }
        }.attach()
    }

    private fun collectData() {
        viewModel.feedBackContent.flowWithLifecycle(lifecycle).onEach { feedBackContent ->
            if (feedBackContent != null) {
                binding.tvChartTitle.text =
                    String.format("%d월 %d주차 통계/피드백", feedBackContent.month, feedBackContent.week)
            }
        }.launchIn(lifecycleScope)
    }

//    override fun onDestroyView() {
//        binding.vpChart.adapter = null
//        super.onDestroyView()
//    }
}
