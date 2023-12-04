package com.example.howdroid.presentation.home

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentHomeBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.UiState
import com.example.howdroid.util.binding.BindingFragment
import com.example.howdroid.util.extension.hideKeyboard
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.example.howdroid.util.extension.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class HomeFragment :
    BindingFragment<FragmentHomeBinding>(R.layout.fragment_home),
    TodoOptionClickListener,
    TodoCheckClickListener,
    HomeBottomSheetListener {

    private val homeViewModel by viewModels<HomeViewModel>()
    private var toDoId by Delegates.notNull<Int>()
    private val outerAdapter by lazy {
        HomeTodoOuterAdapter(
            moveToAddToDo = { categoryId ->
                val bundle = putDataToBundle()
                bundle.putInt(CATEGORY_ID, categoryId)
                findNavController().navigate(R.id.navigation_addToDo, bundle)
            },
            onInnerItemClick = this,
            onInnerToDoCheck = this,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListeners()
        fetchHome()
        showAddCategory()
        addCategory()
        setupTouchEvents()
    }

    override fun onResume() {
        super.onResume()
        fetchHome()
    }

    private fun addCategory() {
        binding.etHomeAddCategoty.setOnEditorActionListener { _, actionId, event ->
            val categoryName = binding.etHomeAddCategoty.text.toString()
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                homeViewModel.postCategory(categoryName)
                fetchHome()
                requireContext().hideKeyboard(binding.root)
                true
            } else {
                false
            }
        }
    }

    override fun onOptionClick(todoItem: Home.TodoData) {
        toDoId = todoItem.todoId
        val bottomSheetFragment = HomeBottomSheetFragment(todoItem)
        bottomSheetFragment.listener = this
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    override fun onCheckClick(todoItem: Home.TodoData) {
        homeViewModel.checkTodo(todoItem.todoId.toLong())
    }

    private fun addListeners() {
        binding.ivHomeToolbarFailtag.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_myFailTagActivity)
        }
        binding.weeklyCalendar.setOnWeeklyDayClickListener { _, date ->
            homeViewModel.setSelectedDate(date.toString())
        }
    }

    override fun onBottomSheetClosed(tag: String?) {
        if (tag == TAG) {
            showPutFailTagBottomFragment()
        }
    }

    private fun showPutFailTagBottomFragment() {
        val bottomSheetFragment = PutFailTagBottomSheetFragment()
            .apply {
                val bundle = putDataToBundle()
                bundle.putInt(TODO_ID, this@HomeFragment.toDoId)
                arguments = bundle
            }
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        binding.clHomeAddCategory.setVisible(GONE)
    }

    private fun putDataToBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(SELECTED_DATE, homeViewModel.selectedDate.value)
        arguments = bundle
        return bundle
    }

    private fun showAddCategory() {
        with(binding) {
            tvHomeAddCategoty.setOnSingleClickListener {
                etHomeAddCategoty.text = null
                clHomeAddCategory.setVisible(VISIBLE)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupTouchEvents() {
        with(binding) {
            root.setOnTouchListener { _, event -> handleTouchEvent(clHomeAddCategory, event) }
            rvOuterHomeTodoList.setOnTouchListener { _, event ->
                handleTouchEvent(
                    clHomeAddCategory,
                    event,
                )
            }
        }
    }

    private fun handleTouchEvent(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val rect = Rect()
            view.getGlobalVisibleRect(rect)
            if (!rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                view.setVisible(GONE)
                return true
            }
        }
        return false
    }

    private fun fetchHome() {
        homeViewModel.getHomeData()
        homeViewModel.homeData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    binding.rvOuterHomeTodoList.adapter = outerAdapter
                    outerAdapter.submitList(uiState.data.todoCategoryData)
                    setHomeTitle(uiState.data.rateOfSuccess)
                }

                else -> Unit
            }
        }
        homeViewModel.selectedDate.observe(viewLifecycleOwner) {
            homeViewModel.getHomeData()
        }
    }

    private fun setHomeTitle(rateOfSuccess: Int) {
        val homeTitleString = getString(R.string.home_title)
        val homeTitle = homeTitleString.replace("%d%", "$rateOfSuccess%")
        val postHomeTitle = SpannableString(homeTitle)
        val startIndex = homeTitle.indexOf(rateOfSuccess.toString())
        val endIndex = startIndex + rateOfSuccess.toString().length + 1

        postHomeTitle.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.Green_300)),
            startIndex,
            endIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
        binding.tvToolbarTitle.text = postHomeTitle
    }

    override fun onDestroyView() {
        binding.rvOuterHomeTodoList.adapter = null
        super.onDestroyView()
    }

    companion object {
        const val TAG = "homeBottomSheetFragmentTag"
        const val SELECTED_DATE = "selectedDate"
        const val CATEGORY_ID = "categoryId"
        const val TODO_ID = "todoId"
    }
}
