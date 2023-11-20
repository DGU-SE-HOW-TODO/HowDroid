package com.example.howdroid.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.howdroid.data.datasource.local.HomeMockData
import com.example.howdroid.domain.model.home.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val homeMockData = HomeMockData()

    private val _homeData = MutableLiveData<List<Home.TodoCategory>>()
    val homeData: LiveData<List<Home.TodoCategory>> get() = _homeData

    fun getHomeData() {
        _homeData.value = homeMockData.mockHomeData.todoCategoryData
    }
}
