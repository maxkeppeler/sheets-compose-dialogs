package com.maxkeppeler.sheets.color.models

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Helper class to simplify passing colors.
 * @param colorInt Value of color as Int.
 * @param colorRes Res of color value.
 * @param colorHex Color value as Hex-String.
 */
data class SingleColor(
    @ColorInt val colorInt: Int? = null,
    @ColorRes val colorRes: Int? = null,
    val colorHex: String? = null,
) {
    fun colorInInt(context: Context): Int? = colorInt
        ?: colorRes?.let { ContextCompat.getColor(context, it) }
        ?: colorHex?.let { Color.parseColor(it) }
}
