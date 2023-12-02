package com.example.howdroid.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("responseMessage")
    val responseMessage: String,

)
