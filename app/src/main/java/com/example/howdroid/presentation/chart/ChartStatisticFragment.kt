package com.example.howdroid.presentation.chart

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.howdroid.R
import com.example.howdroid.data.model.response.ResponseStatistic
import com.example.howdroid.databinding.FragmentChartStatisticBinding
import com.example.howdroid.util.binding.BindingFragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ChartStatisticFragment :
    BindingFragment<FragmentChartStatisticBinding>(R.layout.fragment_chart_statistic) {
    private val viewModel: ChartViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectData()
    }

    private fun collectData() {
        viewModel.rateOfChange.flowWithLifecycle(lifecycle).onEach { rateOfChange ->
            val rateString = String.format("%d%%", rateOfChange)
            val formattedString = getString(
                R.string.chart_statistic_achievement_rate_des,
                Html.fromHtml(rateString, Html.FROM_HTML_MODE_LEGACY)
            )
            binding.tvAchievementRateDes.text =
                Html.fromHtml(formattedString, Html.FROM_HTML_MODE_LEGACY)
        }.launchIn(lifecycleScope)
        viewModel.nowToDoAchievementRate.flowWithLifecycle(lifecycle)
            .onEach { nowToDoAchievementRate ->
                val achievementRateBarChart: BarChart = binding.barChartAchievementRate
                val barList =
                    setAchievementRateData(
                        viewModel.preToDoAchievementRate.value,
                        nowToDoAchievementRate
                    )
                setAchievementRateBarChart(achievementRateBarChart, barList)
            }.launchIn(lifecycleScope)
        viewModel.nowBestCategory.flowWithLifecycle(lifecycle).onEach { nowBestCategory ->
            val content = getString(
                R.string.chart_statistic_category_des,
                Html.fromHtml(nowBestCategory, Html.FROM_HTML_MODE_LEGACY)
            )
            binding.tvCategoryRateDes.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        }.launchIn(lifecycleScope)
        viewModel.nowCategoryRateList.flowWithLifecycle(lifecycle).onEach { nowCategoryRateList ->
            val categoryRatePieChart: PieChart = binding.pieChartCategoryRate
            val categoryPieList = setCategoryRateData(nowCategoryRateList)
            setCategoryRatePieChart(categoryRatePieChart, categoryPieList)
        }.launchIn(lifecycleScope)
        viewModel.nowWorstFailTag.flowWithLifecycle(lifecycle).onEach { nowWorstFailTag ->
            val content = getString(
                R.string.chart_statistic_fail_tag_des,
                Html.fromHtml(nowWorstFailTag, Html.FROM_HTML_MODE_LEGACY)
            )
            binding.tvFailTagRateDes.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        }.launchIn(lifecycleScope)
        viewModel.nowFailTagList.flowWithLifecycle(lifecycle).onEach { nowFailTagList ->
            val failTagRatePieChart: PieChart = binding.pieChartFailTagRate
            val failTagPieList = setFailTagRateData(nowFailTagList)
            setFailTagRatePieChart(failTagRatePieChart, failTagPieList)
        }.launchIn(lifecycleScope)
    }

    private fun setAchievementRateBarChart(barChart: BarChart, barList: ArrayList<BarEntry>) {
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
                valueFormatter = LabelCustomFormatter()
                setDrawGridLines(false)
                granularity = 1f
                setDrawAxisLine(false)
                textSize = 10f
                typeface = Typeface.DEFAULT_BOLD
            }

            axisLeft.run {
                isEnabled = false
                axisMinimum = 0f
                axisMaximum = 100f
                textColor = Color.BLACK
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
                        it.getColor(R.color.Gray_300),
                        it.getColor(R.color.Green_300),
                    )
                }
                valueFormatter = LabelCustomFormatter()
            }

        val data = BarData(barDataSet)
        data.barWidth = 0.35f
        barChart.data = data
        barChart.setFitBars(true)

        barChart.notifyDataSetChanged()
        barChart.invalidate()
    }

    private fun setAchievementRateData(prePercent: Int, nowPercent: Int): ArrayList<BarEntry> {
        val barList = ArrayList<BarEntry>()
        barList.add(BarEntry(1f, prePercent.toFloat()))
        barList.add(BarEntry(2f, nowPercent.toFloat()))

        return barList
    }

    private fun setCategoryRatePieChart(pieChart: PieChart, dataList: ArrayList<PieEntry>) {
        pieChart.run {
            setUsePercentValues(true)
            setTouchEnabled(false)
            context?.getColor(R.color.Gray_50)?.let { setEntryLabelColor(it) }
            setEntryLabelTextSize(13f)
            setEntryLabelTypeface(Typeface.DEFAULT_BOLD)
            description.isEnabled = false
            isDrawHoleEnabled = false
            legend.isEnabled = false
            animateY(1000)
        }

        val pieDataSet = PieDataSet(dataList, "")
        pieDataSet.run {
            colors = context?.let {
                listOf(
                    it.getColor(R.color.Green_300),
                    it.getColor(R.color.Green_200),
                    it.getColor(R.color.Gray_200),
                )
            }
            sliceSpace = 3f
        }

        val data = PieData(pieDataSet)
        data.run {
            setValueFormatter(IntegerPercentFormatter())
            context?.getColor(R.color.Gray_50)?.let { setValueTextColor(it) }
            setValueTextSize(12f)
            setValueTypeface(Typeface.DEFAULT_BOLD)
        }
        pieChart.data = data
        pieChart.invalidate()
    }

    private fun setCategoryRateData(nowCategoryRateList: MutableList<ResponseStatistic.NowCategoryDate>): ArrayList<PieEntry> {
        val pieList = ArrayList<PieEntry>()

        for (nowCategoryRate in nowCategoryRateList) {
            pieList.add(
                PieEntry(
                    nowCategoryRate.nowCategoryRate.toFloat(),
                    nowCategoryRate.nowCategory
                )
            )
        }
        return pieList
    }

    private fun setFailTagRatePieChart(pieChart: PieChart, dataList: ArrayList<PieEntry>) {
        pieChart.run {
            setUsePercentValues(true)
            setTouchEnabled(false)
            context?.getColor(R.color.Gray_50)?.let { setEntryLabelColor(it) }
            setEntryLabelTextSize(13f)
            setEntryLabelTypeface(Typeface.DEFAULT_BOLD)
            description.isEnabled = false
            isDrawHoleEnabled = false
            legend.isEnabled = false
            animateY(1000)
        }

        val pieDataSet = PieDataSet(dataList, "")
        pieDataSet.run {
            colors = context?.let {
                listOf(
                    it.getColor(R.color.Pink_500),
                    it.getColor(R.color.Pink_400),
                    it.getColor(R.color.Pink_300),
                    it.getColor(R.color.Pink_200),
                    it.getColor(R.color.Gray_200),
                )
            }
            sliceSpace = 3f
        }

        val data = PieData(pieDataSet)
        data.run {
            setValueFormatter(IntegerPercentFormatter())
            context?.getColor(R.color.Gray_50)?.let { setValueTextColor(it) }
            setValueTextSize(12f)
            setValueTypeface(Typeface.DEFAULT_BOLD)
        }
        pieChart.data = data
        pieChart.invalidate()
    }

    private fun setFailTagRateData(failTagList: MutableList<ResponseStatistic.NowFailtag>): ArrayList<PieEntry> {
        val pieList = ArrayList<PieEntry>()

        for (failTag in failTagList) {
            pieList.add(PieEntry(failTag.nowFailtagRate.toFloat(), failTag.nowFailtag))
        }

        return pieList
    }
}

class LabelCustomFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val index = value.toInt()
        return when (index) {
            1 -> "저번주"
            2 -> "이번주"
            else -> throw IndexOutOfBoundsException("index out")
        }
    }

    override fun getBarStackedLabel(value: Float, stackedEntry: BarEntry?): String {
        return super.getBarStackedLabel(value, stackedEntry)
    }
}

class IntegerPercentFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return "${value.toInt()}%"
    }
}
