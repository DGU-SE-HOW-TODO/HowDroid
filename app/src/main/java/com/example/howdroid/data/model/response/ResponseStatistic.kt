package com.example.howdroid.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseStatistic(
    val nowBestCategory: String?,
    val nowCategoryDate: List<NowCategoryDate>,
    val nowFailtagList: List<NowFailtag>,
    val nowTodoCnt: Int,
    val nowTodoDoneCnt: Int,
    val nowWorstFailtag: String?,
    val prevTodoCnt: Int,
    val prevTodoDoneCnt: Int,
    val rateOfChange: Int,
    val selectedDate: String
) {
    @Serializable
    data class NowCategoryDate(
        val nowCategory: String,
        val nowCategoryRate: Int
    )
    @Serializable
    data class NowFailtag(
        val nowFailtag: String,
        val nowFailtagRate: Int
    )
}
