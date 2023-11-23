package com.example.howdroid.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivityMainBinding
import com.example.howdroid.presentation.type.PriorityType
import com.example.howdroid.util.binding.BindingActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: AddToDoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.customChipMostImportant.setOnClickListener {
            viewModel.setSelectedPriorityType(PriorityType.MOST_IMPORTANT)
        }

        binding.customChipImportant.setOnClickListener {
            viewModel.setSelectedPriorityType(PriorityType.IMPORTANT)
        }

        binding.customChipNotImportant.setOnClickListener {
            viewModel.setSelectedPriorityType(PriorityType.NOT_IMPORTANT)
        }

        viewModel.priorityType.flowWithLifecycle(lifecycle).onEach { priorityType ->
            binding.customChipMostImportant.isSelected = priorityType == PriorityType.MOST_IMPORTANT
            binding.customChipMostImportant.updateViewColor()
            binding.customChipImportant.isSelected = priorityType == PriorityType.IMPORTANT
            binding.customChipImportant.updateViewColor()
            binding.customChipNotImportant.isSelected = priorityType == PriorityType.NOT_IMPORTANT
            binding.customChipNotImportant.updateViewColor()
        }.launchIn(lifecycleScope)
    }
}
