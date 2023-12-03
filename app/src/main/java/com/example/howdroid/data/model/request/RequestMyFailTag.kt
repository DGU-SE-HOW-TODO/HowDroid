package com.example.howdroid.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestMyFailTag(
    val selectedDate: String,
    val selectedFailtagList: List<String>,
)
