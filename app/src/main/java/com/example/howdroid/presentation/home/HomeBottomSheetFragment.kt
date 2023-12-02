package com.example.howdroid.presentation.home

import android.os.Bundle
import android.view.View
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentTodoDetailBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.binding.BindingBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBottomSheetFragment(private val todoItem: Home.TodoData) :
    BindingBottomSheetDialogFragment<FragmentTodoDetailBinding>(R.layout.fragment_todo_detail) {
    var listener: HomeBottomSheetListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHomeTodoDetailTitle.text = todoItem.todoName

        binding.tvHomeTodoDetailFailtag.setOnClickListener {
            listener?.onBottomSheetClosed(TAG)
            dismiss()
        }
    }

    companion object {
        const val TAG = "homeBottomSheetFragmentTag"
    }
}

interface HomeBottomSheetListener {
    fun onBottomSheetClosed(tag: String?)
}
