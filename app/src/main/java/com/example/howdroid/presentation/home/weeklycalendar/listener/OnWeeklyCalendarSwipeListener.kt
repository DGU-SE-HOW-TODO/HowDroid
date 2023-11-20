package com.example.howdroid.presentation.home.weeklycalendar.listener

import java.time.LocalDate

interface OnWeeklyCalendarSwipeListener {
    fun onSwipe(mondayDate: LocalDate?)
}
