package com.example.howdroid.presentation.home.weeklycalendar.listener

import android.view.View
import java.time.LocalDate

fun interface OnWeeklyDayClickListener {
    fun onWeeklyDayClick(view: View, date: LocalDate)
}
