package com.example.howdroid.presentation.home

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.howdroid.R
import com.example.howdroid.databinding.FragmentHomeBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.binding.BindingFragment
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.example.howdroid.util.extension.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BindingFragment<FragmentHomeBinding>(R.layout.fragment_home),
    TodoOptionClickListener, HomeBottomSheetListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private val outerAdapter by lazy {
        HomeTodoOuterAdapter(
            moveToAddToDo = {
                findNavController().navigate(R.id.navigation_addToDo)
            },
            onInnerItemClick = this,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListeners()
        setHome()
        setHomeTitle()
        addCategory()
    }

    override fun onOptionClick(todoItem: Home.TodoItem) {
        val bottomSheetFragment = HomeBottomSheetFragment(todoItem)
        bottomSheetFragment.listener = this
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    private fun addListeners() {
        binding.ivHomeToolbarFailtag.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_myFailTagActivity)
        }
    }

    override fun onBottomSheetClosed(tag: String?) {
        if (tag == TAG)
            showPutFailTagBottomFragment()
    }

    private fun showPutFailTagBottomFragment() {
        val bottomSheetFragment = PutFailTagBottomSheetFragment()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    private fun addCategory() {
        with(binding) {
            tvHomeAddCategoty.setOnSingleClickListener {
                clHomeAddCategory.setVisible(VISIBLE)
            }
        }
    }

    private fun setHome() {
        homeViewModel.homeData.observe(viewLifecycleOwner) { homeData ->
            binding.rvOuterHomeTodoList.adapter = outerAdapter
            outerAdapter.submitList(homeData)
        }

        homeViewModel.getHomeData()
    }

    private fun setHomeTitle() {
        val homeTitleString = getString(R.string.home_title)
        val toDoPercent = 70 // 나중에 서버에서 받는 값으로 바꾸기
        val homeTitle = homeTitleString.replace("%d%", "$toDoPercent%")
        val postHomeTitle = SpannableString(homeTitle)
        val startIndex = homeTitle.indexOf(toDoPercent.toString())
        val endIndex = startIndex + toDoPercent.toString().length + 1

        postHomeTitle.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.Green_300)),
            startIndex,
            endIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
        binding.tvToolbarTitle.text = postHomeTitle
    }

    companion object {
        const val TAG = "homeBottomSheetFragmentTag"
    }
}
