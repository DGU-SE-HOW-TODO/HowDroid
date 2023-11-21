package com.example.howdroid.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.howdroid.R
import com.example.howdroid.databinding.ActivityAddTodoBinding
import com.example.howdroid.util.UiState
import com.example.howdroid.util.binding.BindingActivity
import com.example.howdroid.util.extension.setVisible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddToDoActivity : BindingActivity<ActivityAddTodoBinding>(R.layout.activity_add_todo) {

    private val addToDoViewModel: MainViewModel by viewModels()

    private val errorMessageTextView by lazy { binding.tvAddTodoErrorMessage }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeLiveData()
        setTextChangeListeners()

        binding.customChipMostImportant.setOnClickListener {
            addToDoViewModel.setSelectedPriorityType(PriorityType.MOST_IMPORTANT)
        }

        binding.customChipImportant.setOnClickListener {
            addToDoViewModel.setSelectedPriorityType(PriorityType.IMPORTANT)
        }

        binding.customChipNotImportant.setOnClickListener {
            addToDoViewModel.setSelectedPriorityType(PriorityType.NOT_IMPORTANT)
        }

        addToDoViewModel.priorityType.flowWithLifecycle(lifecycle).onEach { priorityType ->
            with(binding) {
                when (priorityType) {
                    PriorityType.MOST_IMPORTANT -> {
                        customChipMostImportant.isSelected = true
                        customChipImportant.isSelected = false
                        customChipNotImportant.isSelected = false
                    }

                    PriorityType.IMPORTANT -> {
                        customChipMostImportant.isSelected = false
                        customChipImportant.isSelected = true
                        customChipNotImportant.isSelected = false
                    }

                    PriorityType.NOT_IMPORTANT -> {
                        customChipMostImportant.isSelected = false
                        customChipImportant.isSelected = false
                        customChipNotImportant.isSelected = true
                    }

                    else -> Unit
                }
                customChipMostImportant.updateViewColor()
                customChipImportant.updateViewColor()
                customChipNotImportant.updateViewColor()
            }
        }.launchIn(lifecycleScope)
    }

    private fun observeLiveData() {
        addToDoViewModel.toDoTitle.observe(this) { handleUiState(it, errorMessageTextView) }
        addToDoViewModel.isButtonEnabled.observe(this) { binding.btnAddTodoDone.isEnabled = it }
    }

    private fun setTextChangeListeners() {
        binding.etAddTodo.addTextChangedListener { updateSignUpInfo() }
    }

    private fun handleUiState(uiState: UiState<String>, textView: TextView) {
        with(textView) {
            when (uiState) {
                is UiState.Success -> setVisible(View.INVISIBLE)
                is UiState.Failure -> setVisible(View.VISIBLE)
                is UiState.Empty -> setVisible(View.INVISIBLE)
                else -> Unit
            }
        }
    }

    private fun updateSignUpInfo() {
        val toDoTitle = binding.etAddTodo.text.toString()
        addToDoViewModel.setaddToDoState(toDoTitle)
    }
}
