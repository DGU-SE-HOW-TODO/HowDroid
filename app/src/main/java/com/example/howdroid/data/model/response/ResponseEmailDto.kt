package com.example.howdroid.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseEmailDto(
    @SerialName("statusCode")
    val statusCode: String,
    @SerialName("statusCodeMessage")
    val statusCodeMessage: String,
    @SerialName("statusMessage")
    val statusMessage: String,
)
