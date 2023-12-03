package com.example.howdroid.data.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryDto(
    @SerialName("name")
    val name: String
)