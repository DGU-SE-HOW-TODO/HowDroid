package com.example.howdroid.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentTodoDetailBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.binding.BindingBottomSheetDialogFragment
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.example.howdroid.util.extension.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBottomSheetFragment(private val todoItem: Home.TodoData) :
    BindingBottomSheetDialogFragment<FragmentTodoDetailBinding>(R.layout.fragment_todo_detail) {
    var listener: HomeBottomSheetListener? = null
    private val homeBottomSheetViewModel by viewModels<HomeBottomSheetViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()
        clickFailTag()
        fixToDo()
    }

    private fun observeFixToDo() {
        homeBottomSheetViewModel.fixToDo.observe(viewLifecycleOwner) { result ->
            if (result) {
                dismiss()
            }
        }
    }

    private fun fixToDo() {
        binding.clHomeTodoFix.setOnSingleClickListener {
            if (!todoItem.isFixed) {
                homeBottomSheetViewModel.fixToDo(todoItem.todoId.toLong())
                observeFixToDo()
            } else {
                showSnackbar(binding.root, getString(R.string.home_todo_fix_fail_message))
            }
        }
    }

    private fun clickFailTag() {
        binding.clHomeTodoFailtag.setOnClickListener {
            listener?.onBottomSheetClosed(TAG)
            dismiss()
        }
    }

    private fun setTitle() {
        binding.tvHomeTodoDetailTitle.text = todoItem.todoName
    }

    companion object {
        const val TAG = "homeBottomSheetFragmentTag"
    }
}

interface HomeBottomSheetListener {
    fun onBottomSheetClosed(tag: String?)
}
