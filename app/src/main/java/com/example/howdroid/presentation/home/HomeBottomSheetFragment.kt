package com.example.howdroid.presentation.home

import android.os.Bundle
import android.view.View
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentTodoDetailBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.binding.BindingBottomSheetDialogFragment

class HomeBottomSheetFragment(private val todoItem: Home.TodoItem) :
    BindingBottomSheetDialogFragment<FragmentTodoDetailBinding>(R.layout.fragment_todo_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHomeTodoDetailTitle.text = todoItem.todo
    }
}
