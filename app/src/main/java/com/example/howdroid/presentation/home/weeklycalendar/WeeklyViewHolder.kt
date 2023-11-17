package com.example.howdroid.presentation.home.weeklycalendar

import android.text.format.DateUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.howdroid.R
import com.example.howdroid.databinding.ViewWeeklyCalendarDayBinding
import com.example.howdroid.presentation.home.weeklycalendar.listener.OnWeeklyDayClickListener
import com.example.howdroid.presentation.home.weeklycalendar.util.dayNameParseToKorea
import java.sql.Date
import java.time.LocalDate

class WeeklyViewHolder(
    private val binding: ViewWeeklyCalendarDayBinding,
    private val onWeeklyDayClickListener: OnWeeklyDayClickListener,
) : ViewHolder(binding.root), View.OnClickListener {

    private lateinit var weeklyDate: LocalDate

    init {
        binding.root.setOnClickListener(this)
    }

    fun onBind(weeklyDate: LocalDate) {
        val date: Date = Date.valueOf(weeklyDate.toString()) as Date
        this.weeklyDate = weeklyDate
        with(binding) {
            day = weeklyDate.dayOfMonth.toString()
            dayString = weeklyDate.dayOfWeek.name.dayNameParseToKorea()

            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.Gray_50,
                ),
            )
            ivDate.setImageDrawable(
                ContextCompat.getDrawable(
                    ivDate.context,
                    if (DateUtils.isToday(date.time)) R.drawable.shape_green_circle else R.drawable.shape_circle,
                ),
            )
        }
    }

    fun onSelectBind(weeklyDate: LocalDate) {
        val date: Date = Date.valueOf(weeklyDate.toString()) as Date
        this.weeklyDate = weeklyDate
        with(binding) {
            day = weeklyDate.dayOfMonth.toString()
            dayString = weeklyDate.dayOfWeek.name.dayNameParseToKorea()

            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.Gray_50,
                ),
            )
            ivDate.setImageDrawable(
                ContextCompat.getDrawable(
                    ivDate.context,
                    if (DateUtils.isToday(date.time)) R.drawable.shape_green_circle else R.drawable.shape_gray_circle,
                ),
            )
        }
    }

    override fun onClick(view: View) {
        onWeeklyDayClickListener.onWeeklyDayClick(view, weeklyDate)
    }
}
