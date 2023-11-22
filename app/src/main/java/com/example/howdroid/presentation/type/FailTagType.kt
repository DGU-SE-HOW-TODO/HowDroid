package com.example.howdroid.presentation.type

import androidx.annotation.StringRes
import com.example.howdroid.R

enum class FailTagType(@StringRes val titleRes: Int) {
    SLEEP(R.string.my_fail_tag_sleep),
    SICK(R.string.my_fail_tag_sick),
    SUDDEN_SCHEDULE(R.string.my_fail_tag_sudden_schedule),
    WEAK_WILL(R.string.my_fail_tag_weak_will),
    ON_TIME(R.string.my_fail_tag_on_time),
    SNS(R.string.my_fail_tag_sns),
    GAME(R.string.my_fail_tag_game),
    TIME_MANAGEMENT_FAILURE(R.string.my_fail_tag_time_management_failure),
    UNREASONABLE_PLAN(R.string.my_fail_tag_unreasonable_plan),
    LOW_MOTIVATION(R.string.my_fail_tag_low_motivation),
    CHANGE_SCHEDULE(R.string.my_fail_tag_change_schedule),
    REST(R.string.my_fail_tag_rest),
    SLUMP(R.string.my_fail_tag_slump),
    LACK_CONCENTRATION(R.string.my_fail_tag_lack_concentration),
    PHONE_ADDICTION(R.string.my_fail_tag_phone_addiction),
    LAZY(R.string.my_fail_tag_lazy),
    BAD_CONDITION(R.string.my_fail_tag_bad_condition),
}
