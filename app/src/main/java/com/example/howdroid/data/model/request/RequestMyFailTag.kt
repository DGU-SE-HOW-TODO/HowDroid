package com.example.howdroid.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestMyFailTag(
    val month: Int,
    val selectedFailtagList: List<String>,
    val week: Int,
    val year: Int
)
