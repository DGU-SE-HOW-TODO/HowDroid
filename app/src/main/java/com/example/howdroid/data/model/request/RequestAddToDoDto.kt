package com.example.howdroid.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestAddToDoDto(
    @SerialName("priority")
    val priority: String,
    @SerialName("selectedDate")
    val selectedDate: String,
    @SerialName("todo")
    val todo: String,
    @SerialName("todoCategoryId")
    val todoCategoryId: Int,
)
