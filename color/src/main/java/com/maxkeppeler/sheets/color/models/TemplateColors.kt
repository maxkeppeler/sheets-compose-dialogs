package com.maxkeppeler.sheets.color.models

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

sealed class TemplateColors(
    @ColorInt private var colorsInt: Array<Int>? = null,
    @ColorRes private var colorsRes: Array<Int>? = null,
    private var colorsHex: Array<String>? = null,
) {

    class ColorsRes : TemplateColors {
        constructor(@ColorRes vararg colors: Int) : super(colorsRes = colors.toTypedArray())
        constructor(@ColorRes colors: List<Int>) : super(colorsRes = colors.toTypedArray())
    }

    class ColorsInt : TemplateColors {
        constructor(@ColorInt vararg colors: Int) : super(colorsInt = colors.toTypedArray())
        constructor(@ColorInt colors: List<Int>) : super(colorsInt = colors.toTypedArray())
    }

    class ColorsHex : TemplateColors {
        constructor(vararg colors: String) : super(colorsHex = colors.toList().toTypedArray())
        constructor(colors: List<String>) : super(colorsHex = colors.toTypedArray())
    }

    fun getColorsAsInt(context: Context): List<Int> {
        return colorsInt?.toList()
            ?: colorsRes?.map { ContextCompat.getColor(context, it) }
            ?: colorsHex?.map { Color.parseColor(it) }
            ?: throw IllegalStateException("No colors available for color templates view. Either disabled template view or add colors.")
    }
}
