package com.example.howdroid.util

import android.view.LayoutInflater
import com.example.howdroid.R
import com.google.android.material.chip.Chip

object ChipFactory {
    fun create(layoutInflater: LayoutInflater): Chip =
        layoutInflater.inflate(R.layout.view_fail_tag_chip, null, false) as Chip
}
