package com.example.howdroid.presentation.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentChartStatisticBinding
import com.example.howdroid.util.binding.BindingFragment

class ChartFeedbackFragment :
    BindingFragment<FragmentChartStatisticBinding>(R.layout.fragment_chart_feedback) {
    private val viewModel: ChartViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
