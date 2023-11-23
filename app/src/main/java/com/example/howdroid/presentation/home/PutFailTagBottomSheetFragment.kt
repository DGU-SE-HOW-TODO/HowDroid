package com.example.howdroid.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentBottomPutFaillTagBinding
import com.example.howdroid.util.binding.BindingBottomSheetDialogFragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PutFailTagBottomSheetFragment :
    BindingBottomSheetDialogFragment<FragmentBottomPutFaillTagBinding>(
        R.layout.fragment_bottom_put_faill_tag
    ) {
    // TODO 임시 데이터 삭제
    private val mockMyFailTagList = listOf("잠", "정각병", "SNS", "게임")
    private val viewModel: PutFailTagViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMyFailTag()
        addListeners()
        observeData()
        collectData()
    }

    private fun setMyFailTag() {
        val inflater = LayoutInflater.from(requireContext())
        for (failTagName in mockMyFailTagList) {
            val chip = inflater.inflate(
                R.layout.view_fail_tag_chip,
                binding.cgPutFailTag,
                false
            ) as Chip
            chip.apply {
                id = View.generateViewId()
                text = String.format("# %s", failTagName)
            }
            binding.cgPutFailTag.addView(chip)
        }
    }

    private fun addListeners() {
        binding.btnCompletePutFailTag.setOnClickListener {
            dismiss()
        }
        binding.cbPutFailTagDelayTillTomorrow.setOnClickListener {
            viewModel.setIsDelayedTomorrow()
        }
    }

    // TODO 로직 수정 필요, TS
    private fun observeData() {
        // update selected chip
        binding.cgPutFailTag.setOnCheckedStateChangeListener { group, checkedId ->
            if (group.checkedChipId != -1) {
                val checkedChip = group.findViewById<Chip>(group.checkedChipId)
                val checkedChipTitle = checkedChip.text.toString()
                for (chipTitleWithoutHashTag in mockMyFailTagList) {
                    if (checkedChipTitle.contains(chipTitleWithoutHashTag))
                        viewModel.setSelectedFailTag(chipTitleWithoutHashTag)
                }
            } else {
                viewModel.setSelectedFailTag(null)
            }
        }
    }

    private fun collectData() {
        viewModel.selectedFailTag.flowWithLifecycle(lifecycle).onEach { selectedFailTag ->
            binding.btnCompletePutFailTag.isEnabled = selectedFailTag != null
            binding.cbPutFailTagDelayTillTomorrow.isEnabled = selectedFailTag != null
            binding.tvDelayedTomorrow.isEnabled = selectedFailTag != null
        }.launchIn(lifecycleScope)
        viewModel.isDelayedTomorrow.flowWithLifecycle(lifecycle).onEach { isDelayedTomorrow ->
            binding.tvDelayedTomorrow.isSelected = isDelayedTomorrow
        }.launchIn(lifecycleScope)
    }
}
