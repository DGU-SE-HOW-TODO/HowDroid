package com.example.howdroid.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseFeedBack(
    val month: Int,
    val week: Int,
    val delayDetailMessage: String,
    val delayMessage: String,
    val firstPriPercent: Int,
    val priorityDetailMessage: String,
    val priorityMessage: String,
    val rateDetailMessage: String,
    val rateMessage: String,
    val secondPriPercent: Int,
    val thirdPriPercent: Int
)
