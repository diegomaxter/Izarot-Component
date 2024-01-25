package com.example.nuevo

import androidx.annotation.DrawableRes

enum class ItemStyle(
    val value: Int,
    @DrawableRes val backgroundDrawable: Int,
) {
    Rounded(0, R.drawable.rounded_background),
    Default(1, R.drawable.default_background),;

    companion object {
        fun from(value: Int): ItemStyle? = entries.firstOrNull { it.value == value }
    }
}
