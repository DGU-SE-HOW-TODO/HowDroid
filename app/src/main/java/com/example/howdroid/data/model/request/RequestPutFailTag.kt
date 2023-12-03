package com.example.howdroid.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestPutFailTag(
    val failTagName: String,
    val isDelay: Boolean
)