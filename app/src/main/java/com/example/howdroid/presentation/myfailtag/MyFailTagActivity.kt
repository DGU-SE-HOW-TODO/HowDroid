package com.example.howdroid.presentation.myfailtag

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivityMyFailTagBinding
import com.example.howdroid.presentation.home.HomeFragment
import com.example.howdroid.presentation.type.FailTagType
import com.example.howdroid.util.ChipFactory
import com.example.howdroid.util.UiState
import com.example.howdroid.util.binding.BindingActivity
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyFailTagActivity : BindingActivity<ActivityMyFailTagBinding>(R.layout.activity_my_fail_tag) {
    private val viewModel: MyFailTagViewModel by viewModels()
    private val list = mutableListOf<String>()
    private val String.toChip: Chip
        get() = ChipFactory.create(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setMyFailTag()
        addListeners()
        addObserver()
        clickBackButton()
    }

    private fun addListeners() {
        val selectedDate = intent.extras?.getString(HomeFragment.SELECTED_DATE).toString()
        binding.btnCompleteMyFailTag.setOnClickListener {
            for (i in binding.chipGroupMyFailTag.checkedChipIds) {
                list.add(getString(FailTagType.values().get(i - 1).titleRes))
            }
            viewModel.setMyFailTag(list, selectedDate)
        }
    }

    private fun addObserver() {
        binding.chipGroupMyFailTag.setOnCheckedStateChangeListener { group, _ ->
            val checkedChipCnt = group.checkedChipIds.size

            if (checkedChipCnt >= 5) {
                for (i in 0 until group.childCount) {
                    val chip = group.getChildAt(i) as Chip
                    if (!chip.isChecked) {
                        chip.isEnabled = false
                    }
                }
            } else {
                for (i in 0 until group.childCount) {
                    val chip = group.getChildAt(i) as Chip
                    chip.isEnabled = true
                }
            }
            binding.btnCompleteMyFailTag.isEnabled = checkedChipCnt > 0
        }

        viewModel.isSetFailTagSuccess.flowWithLifecycle(lifecycle).onEach {
            list.clear()
            when (it) {
                is UiState.Success -> finish()
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun setMyFailTag() {
        for (i in FailTagType.values()) {
            val chip = getString(i.titleRes).toChip.apply {
                id = View.generateViewId() // 각 Chip에 고유한 ID 부여, TS
                text = String.format("# %s", getString(i.titleRes))
            }
            binding.chipGroupMyFailTag.addView(chip)
        }
    }

    private fun clickBackButton() {
        binding.myFailTagToolbar.ivToolbarBack.setOnSingleClickListener {
            finish()
        }
    }
}
