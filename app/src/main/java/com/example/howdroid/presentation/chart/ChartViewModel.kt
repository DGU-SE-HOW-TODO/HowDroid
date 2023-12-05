package com.example.howdroid.presentation.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.howdroid.data.model.response.ResponseFeedBack
import com.example.howdroid.data.model.response.ResponseStatistic
import com.example.howdroid.domain.repository.ChartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val chartRepository: ChartRepository
) : ViewModel() {
    private val _preToDoAchievementRate = MutableStateFlow(0)
    val preToDoAchievementRate get() = _preToDoAchievementRate.asStateFlow()
    private val _nowToDoAchievementRate = MutableStateFlow(0)
    val nowToDoAchievementRate get() = _nowToDoAchievementRate.asStateFlow()
    private val _rateOfChange = MutableStateFlow(0)
    val rateOfChange get() = _rateOfChange.asStateFlow()
    private val _nowCategoryRateList =
        MutableStateFlow<MutableList<ResponseStatistic.NowCategoryDate>>(
            mutableListOf()
        )
    val nowCategoryRateList get() = _nowCategoryRateList.asStateFlow()
    private val _nowBestCategory = MutableStateFlow("")
    val nowBestCategory get() = _nowBestCategory.asStateFlow()
    private val _nowFailTagList = MutableStateFlow<MutableList<ResponseStatistic.NowFailtag>>(
        mutableListOf()
    )
    val nowFailTagList get() = _nowFailTagList.asStateFlow()
    private val _nowWorstFailTag = MutableStateFlow("")
    val nowWorstFailTag get() = _nowWorstFailTag.asStateFlow()

    private val _feedBackContent = MutableStateFlow<ResponseFeedBack?>(null)
    val feedBackContent get() = _feedBackContent.asStateFlow()

    init {
        fetchStatistic()
        fetchFeedBack()
    }

    // TODO 초기화 유사한 거 끼리 함수화
    private fun fetchStatistic() {
        val today = LocalDate.now()
        viewModelScope.launch {
            chartRepository.fetchStatistic(today.toString())
                .onSuccess { chartInfo ->
                    val prePercent =
                        chartInfo.prevTodoDoneCnt.toFloat() / chartInfo.prevTodoCnt.toFloat() * 100
                    val nowPercent =
                        chartInfo.nowTodoDoneCnt.toFloat() / chartInfo.nowTodoCnt.toFloat() * 100
                    val totalPercent = prePercent + nowPercent
                    _preToDoAchievementRate.value =
                        (prePercent / totalPercent * 100).toInt()
                    _nowToDoAchievementRate.value = (nowPercent / totalPercent * 100).toInt()
                    _rateOfChange.value = chartInfo.rateOfChange
                    _nowCategoryRateList.value = chartInfo.nowCategoryDate.toMutableList()
                    _nowFailTagList.value = chartInfo.nowFailtagList.toMutableList()
                    _nowBestCategory.value = chartInfo.nowBestCategory
                    _nowWorstFailTag.value = chartInfo.nowWorstFailtag
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    private fun fetchFeedBack() {
        val today = LocalDate.now().toString()
        viewModelScope.launch {
            chartRepository.fetchFeedBack(today)
                .onSuccess { feedBackContent ->
                    _feedBackContent.value = feedBackContent
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }
}
