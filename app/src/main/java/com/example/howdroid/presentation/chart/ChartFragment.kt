package com.example.howdroid.presentation.chart

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentChartBinding
import com.example.howdroid.util.binding.BindingFragment
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate

@AndroidEntryPoint
class ChartFragment : BindingFragment<FragmentChartBinding>(R.layout.fragment_chart) {
    private val viewModel: ChartViewModel by activityViewModels()
    lateinit var selectedDate: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setChartPagerAdapter()
        addListeners()
        collectData()
    }

    override fun onResume() {
        super.onResume()
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

    // TODO 로직 수정 필요
    private fun addListeners() {
        binding.ivChartBack.setOnSingleClickListener {
            val selectedDate = selectedDate.split("-")
            val year = selectedDate[0]
            val month = selectedDate[1]
            val day = selectedDate[2]

            val preDate =
                if (day.toInt() - 7 < 0) String.format(
                    "%s-%02d-%02d",
                    year,
                    month.toInt() - 1,
                    day.toInt() - 7 + 30
                ) else String.format(
                    "%s-%02d-%02d",
                    year,
                    month.toInt(),
                    day.toInt() - 7
                )
            viewModel.fetchStatistic(preDate)
            viewModel.fetchFeedBack(preDate)
        }
        binding.ivChartForward.setOnSingleClickListener {
            val selectedDate = selectedDate.split("-")
            val year = selectedDate[0]
            val month = selectedDate[1]
            val day = selectedDate[2]

            val preDate =
                if (day.toInt() + 7 > 30) String.format(
                    "%s-%02d-%02d",
                    year,
                    month.toInt() + 1,
                    day.toInt() + 7 - 30
                ) else String.format(
                    "%s-%02d-%02d",
                    year,
                    month.toInt(),
                    day.toInt() + 7
                )
            viewModel.fetchStatistic(preDate)
            viewModel.fetchFeedBack(preDate)
        }
    }

    private fun collectData() {
        viewModel.feedBackContent.flowWithLifecycle(lifecycle).onEach { feedBackContent ->
            if (feedBackContent != null) {
                binding.tvChartTitle.text =
                    String.format(
                        getString(R.string.chart_title),
                        feedBackContent.month,
                        feedBackContent.week
                    )
            }
        }.launchIn(lifecycleScope)
        viewModel.selectedDate.flowWithLifecycle(lifecycle).onEach { selectedDate ->
            this.selectedDate = selectedDate
            binding.ivChartForward.isVisible = selectedDate != LocalDate.now().toString()
        }.launchIn(lifecycleScope)
    }
}
