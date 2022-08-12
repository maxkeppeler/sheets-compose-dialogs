package com.maxkeppeler.sheets.color.models

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

data class SelectedColor(
    @ColorInt val selectedColorInt: Int? = null,
    @ColorInt val selectedColorRes: Int? = null,
    val selectedColorHex: String? = null,
) {
    fun colorInInt(context: Context): Int? {
        return selectedColorInt
            ?: selectedColorRes?.let { ContextCompat.getColor(context, it) }
            ?: selectedColorHex?.let { Color.parseColor(it) }
    }
}
