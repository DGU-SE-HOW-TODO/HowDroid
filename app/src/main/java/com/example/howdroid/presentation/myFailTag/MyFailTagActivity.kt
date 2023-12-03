package com.example.howdroid.presentation.myFailTag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivityMyFailTagBinding
import com.example.howdroid.presentation.type.FailTagType
import com.example.howdroid.util.binding.BindingActivity
import com.google.android.material.chip.Chip

class MyFailTagActivity : BindingActivity<ActivityMyFailTagBinding>(R.layout.activity_my_fail_tag) {
    private val viewModel: MyFailTagViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setMyFailTag()
        addListeners()
        addObserver()
    }

    private fun addListeners() {
        val list = mutableListOf<String>()
        binding.btnCompleteMyFailTag.setOnClickListener {
            for (i in binding.chipGroupMyFailTag.checkedChipIds) {
                list.add(getString(FailTagType.values().get(i - 1).titleRes))
            }
            list.removeAll(list)
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
    }

    private fun setMyFailTag() {
        val inflater = LayoutInflater.from(this)
        for (i in FailTagType.values()) {
            val chip = inflater.inflate(
                R.layout.view_fail_tag_chip,
                binding.chipGroupMyFailTag,
                false,
            ) as Chip
            chip.apply {
                id = View.generateViewId() // 각 Chip에 고유한 ID 부여, TS
                text = String.format("# %s", getString(i.titleRes))
            }
            binding.chipGroupMyFailTag.addView(chip)
        }
    }
}
