package com.example.howdroid.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("apiStatus")
    val apiStatus: ApiStatus,
    @SerialName("data")
    val `data`: Data,
) {
    @Serializable
    data class ApiStatus(
        @SerialName("statusCode")
        val statusCode: String,
        @SerialName("statusCodeMessage")
        val statusCodeMessage: String,
        @SerialName("statusMessage")
        val statusMessage: String,
    )

    @Serializable
    data class Data(
        @SerialName("responseMessage")
        val responseMessage: String,
    )
}
