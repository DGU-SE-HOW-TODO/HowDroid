package com.example.howdroid.presentation.chart

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentChartFeedbackBinding
import com.example.howdroid.presentation.type.PriorityType
import com.example.howdroid.util.binding.BindingFragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChartFeedbackFragment :
    BindingFragment<FragmentChartFeedbackBinding>(R.layout.fragment_chart_feedback) {
    private val viewModel: ChartViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectData()
    }

    private fun collectData() {
        viewModel.feedBackContent.flowWithLifecycle(lifecycle).onEach { feedBackContent ->
            with(binding) {
                binding.tvFeedbackTitle.text = String.format("%d주차 피드백", feedBackContent?.week)
                if (feedBackContent?.rateMessage != null) {
                    feedBackContent.also {
                        tvChartRateTitle.text = it.rateMessage
                        tvChartRateDes.text = it.rateDetailMessage
                    }
                    tvChartRateTitle.text = feedBackContent.rateMessage
                    tvChartRateDes.text = feedBackContent.rateDetailMessage
                }
                if (feedBackContent?.priorityMessage != null) {
                    barChartPriorityRate.visibility = View.VISIBLE
                    tvChartPriorityTitle.text = feedBackContent.priorityMessage
                    tvChartPriorityDes.text = feedBackContent.priorityDetailMessage
                }
                if (feedBackContent?.delayMessage != null) {
                    tvChartDelayedTitle.text = feedBackContent.delayMessage
                    tvChartDelayedDes.text = feedBackContent.delayDetailMessage
                }
            }

            val priorityRateChart: BarChart = binding.barChartPriorityRate
            val totalPercent = feedBackContent?.let {
                it.firstPriPercent + it.secondPriPercent + it.thirdPriPercent
            }
            val barList =
                if (feedBackContent != null && totalPercent != null) {
                    setPriorityRateData(
                        feedBackContent.firstPriPercent.toFloat() / totalPercent.toFloat() * 100,
                        feedBackContent.secondPriPercent.toFloat() / totalPercent.toFloat() * 100,
                        feedBackContent.thirdPriPercent.toFloat() / totalPercent.toFloat() * 100
                    )
                } else {
                    return@onEach
                }
            setPriorityRateBarChart(priorityRateChart, barList!!)
        }.launchIn(lifecycleScope)
    }

    private fun setPriorityRateBarChart(barChart: BarChart, barList: ArrayList<BarEntry>) {
        barChart.run {
            setDrawBarShadow(false)
            setTouchEnabled(false)
            setDrawValueAboveBar(true)
            setPinchZoom(false)
            setDrawGridBackground(false)
            setMaxVisibleValueCount(2)
            description.isEnabled = false
            legend.isEnabled = false

            xAxis.run {
                isEnabled = true
                position = XAxis.XAxisPosition.BOTTOM
                valueFormatter = LabelFeedBackCustomFormatter()
                setDrawGridLines(false)
                granularity = 1f
                setDrawAxisLine(false)
                textSize = 13f
                typeface = Typeface.DEFAULT_BOLD
            }

            axisLeft.run {
                isEnabled = false
                axisMinimum = 0f
                axisMaximum = 100f
            }
            axisRight.run {
                isEnabled = false
            }

            animateY(1500)
        }

        val barDataSet = BarDataSet(barList, "")
            .apply {
                setDrawValues(true)
                colors = context?.let {
                    listOf(
                        it.getColor(R.color.Orange),
                        it.getColor(R.color.Green_400),
                        it.getColor(R.color.Blue)
                    )
                }
                valueFormatter = LabelFeedBackCustomFormatter()
            }

        val data = BarData(barDataSet)
        data.barWidth = 0.35f
        barChart.data = data
        barChart.setFitBars(true)

        barChart.notifyDataSetChanged()
        barChart.invalidate()
    }

    private fun setPriorityRateData(
        mostImportant: Float,
        important: Float,
        notImportant: Float
    ): ArrayList<BarEntry> {
        val barList = ArrayList<BarEntry>()
        barList.add(BarEntry(1f, mostImportant))
        barList.add(BarEntry(2f, important))
        barList.add(BarEntry(3f, notImportant))

        return barList
    }
}

class LabelFeedBackCustomFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val index = value.toInt()
        return when (index) {
            1 -> PriorityType.MOST_IMPORTANT.typeString
            2 -> PriorityType.IMPORTANT.typeString
            3 -> PriorityType.NOT_IMPORTANT.typeString
            else -> throw IndexOutOfBoundsException("index out")
        }
    }

    override fun getBarStackedLabel(value: Float, stackedEntry: BarEntry?): String {
        return super.getBarStackedLabel(value, stackedEntry)
    }
}
