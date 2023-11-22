package com.example.howdroid.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentBottomPutFaillTagBinding
import com.example.howdroid.util.binding.BindingBottomSheetDialogFragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PutFailTagBottomSheetFragment :
    BindingBottomSheetDialogFragment<FragmentBottomPutFaillTagBinding>(
        R.layout.fragment_bottom_put_faill_tag
    ) {
    // TODO 임시 데이터 삭제
    private val mockMyFailTagList = listOf("잠", "정각병", "SNS", "게임")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListeners()
        setMyFailTag()
    }

    private fun addListeners() {
        binding.btnCompletePutFailTag.setOnClickListener {
            dismiss()
        }
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
}
