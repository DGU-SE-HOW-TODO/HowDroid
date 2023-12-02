package com.example.howdroid.domain.repository

import com.example.howdroid.domain.model.home.Home

interface HomeRepository {

    suspend fun getHomeData(selectedDate: String): Result<Home>
}
