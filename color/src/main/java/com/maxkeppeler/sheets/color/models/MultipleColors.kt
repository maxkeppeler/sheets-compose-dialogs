/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
@file:Suppress("unused")

package com.maxkeppeler.sheets.color.models

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

sealed class MultipleColors(
    @ColorInt private var colorsInt: Array<Int>? = null,
    @ColorRes private var colorsRes: Array<Int>? = null,
    private var colorsHex: Array<String>? = null,
) {

    class ColorsRes : MultipleColors {
        constructor(@ColorRes vararg colors: Int) : super(colorsRes = colors.toTypedArray())
        constructor(@ColorRes colors: List<Int>) : super(colorsRes = colors.toTypedArray())
    }

    class ColorsInt : MultipleColors {
        constructor(@ColorInt vararg colors: Int) : super(colorsInt = colors.toTypedArray())
        constructor(@ColorInt colors: List<Int>) : super(colorsInt = colors.toTypedArray())
    }

    class ColorsHex : MultipleColors {
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
