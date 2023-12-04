package com.example.howdroid.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyFailTag(
    val failtags: List<String>,
)
