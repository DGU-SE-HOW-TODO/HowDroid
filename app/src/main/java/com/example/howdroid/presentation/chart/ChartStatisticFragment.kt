package com.example.howdroid.presentation.chart

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import com.example.howdroid.R
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

class ChartStatisticFragment :
    BindingFragment<FragmentChartStatisticBinding>(R.layout.fragment_chart_statistic) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val achievementRateBarChart: BarChart = binding.barChartAchievementRate
        val barList = setAchievementRateData(achievementRateBarChart)
        setAchievementRateBarChart(achievementRateBarChart, barList)

        val categoryRatePieChart: PieChart = binding.pieChartCategoryRate
        val categoryPieList = setCategporyRateData()
        setCategoryRatePieChart(categoryRatePieChart, categoryPieList)

        val failTagRatePieChart: PieChart = binding.pieChartFailTagRate
        val failTagPieList = setFailTagRateData()
        setFailTagRatePieChart(failTagRatePieChart, failTagPieList)
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
                        it.getColor(R.color.Gray_300),
                        it.getColor(R.color.Green_300)
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

    private fun setAchievementRateData(barChart: BarChart): ArrayList<BarEntry> {
        val barList = ArrayList<BarEntry>()
        // 임시 데이터 연동
        barList.add(BarEntry(1f, 60f))
        barList.add(BarEntry(2f, 100f))

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
                    it.getColor(R.color.Gray_200),
                    it.getColor(R.color.Green_200),
                    it.getColor(R.color.Green_300),
                )
            }
            sliceSpace = 3f
        }

        val data = PieData(pieDataSet)
        data.run {
            setValueFormatter(IntegerPercentFormatter())
            context?.getColor(R.color.Gray_50)?.let { setValueTextColor(it) }
            setValueTextSize(13f)
            setValueTypeface(Typeface.DEFAULT_BOLD)
        }
        pieChart.data = data
        pieChart.invalidate()
    }

    private fun setCategporyRateData(): ArrayList<PieEntry> {
        val pieList = ArrayList<PieEntry>()

        pieList.add(PieEntry(28f, "취미"))
        pieList.add(PieEntry(32f, "운동"))
        pieList.add(PieEntry(40f, "공부"))

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
                    it.getColor(R.color.Gray_200),
                    it.getColor(R.color.Pink_200),
                    it.getColor(R.color.Pink_300),
                    it.getColor(R.color.Pink_400),
                    it.getColor(R.color.Pink_500)
                )
            }
            sliceSpace = 3f
        }

        val data = PieData(pieDataSet)
        data.run {
            setValueFormatter(IntegerPercentFormatter())
            context?.getColor(R.color.Gray_50)?.let { setValueTextColor(it) }
            setValueTextSize(13f)
            setValueTypeface(Typeface.DEFAULT_BOLD)
        }
        pieChart.data = data
        pieChart.invalidate()
    }

    private fun setFailTagRateData(): ArrayList<PieEntry> {
        val pieList = ArrayList<PieEntry>()

        pieList.add(PieEntry(5f, "잠"))
        pieList.add(PieEntry(10f, "일정변경"))
        pieList.add(PieEntry(20f, "갑작스런일정"))
        pieList.add(PieEntry(30f, "무리한계획"))
        pieList.add(PieEntry(35f, "의지박약"))

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