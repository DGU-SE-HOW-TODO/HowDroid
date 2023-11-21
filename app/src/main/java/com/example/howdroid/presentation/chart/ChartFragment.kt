package com.example.howdroid.presentation.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentChartBinding
import com.example.howdroid.util.binding.BindingFragment
import com.google.android.material.tabs.TabLayoutMediator

class ChartFragment : BindingFragment<FragmentChartBinding>(R.layout.fragment_chart) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setChartPagerAdapter()
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
}
