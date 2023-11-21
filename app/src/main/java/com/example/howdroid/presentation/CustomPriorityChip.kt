package com.example.howdroid.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.howdroid.R
import com.example.howdroid.databinding.ViewPriorityChipBinding

class CustomPriorityChip(
    context: Context,
    attrs: AttributeSet,
) : ConstraintLayout(context, attrs) {
    private var binding: ViewPriorityChipBinding =
        ViewPriorityChipBinding.inflate(LayoutInflater.from(context), this, true)
    var circleColor: Int = Color.WHITE
    var selectedBackgroundDrawable: Drawable? = null

    init {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.priorityChip,
            0,
            0,
        )
        initView(typedArray)
    }

    private fun initView(typedArray: TypedArray) {
        typedArray.apply {
            try {
                val title = getString(R.styleable.priorityChip_name)
                binding.tvPriorityChip.text = title

                circleColor = getColor(R.styleable.priorityChip_selectedColor, Color.WHITE)
                binding.ivPriorityColor.backgroundTintList = ColorStateList.valueOf(circleColor)

                selectedBackgroundDrawable =
                    getDrawable(R.styleable.priorityChip_selectedBackgroundResource)
                binding.root.background = selectedBackgroundDrawable
            } finally {
                recycle()
            }
        }
    }

    fun updateViewColor() {
        binding.tvPriorityChip.setTextColor(
            if (isSelected) circleColor else context.getColor(R.color.Gray_400),
        )
        binding.root.background =
            if (isSelected) selectedBackgroundDrawable else context.getDrawable(R.drawable.background_priority_unselected)
    }
}
