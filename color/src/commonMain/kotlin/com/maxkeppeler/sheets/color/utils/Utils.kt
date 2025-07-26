/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeler.sheets.color.utils

import androidx.compose.ui.graphics.Color

/** Receive the clipboard data. */
@OptIn(ExperimentalStdlibApi::class)
internal fun getFormattedColor(color: Int): String {
    val c = Color(color)
    return toArgbHexManual(c.alpha * 255F, c.red * 255F, c.green * 255F, c.blue * 255F)
}

private fun toArgbHexManual(a: Float, r: Float, g: Float, b: Float): String {
    require(a in 0F..255F && r in 0F..255F && g in 0F..255F && b in 0F..255F) {
        "ARGB values must be between 0 and 255."
    }
    return "#${a.toInt().toHex()}${r.toInt().toHex()}${g.toInt().toHex()}${b.toInt().toHex()}"
}

private fun Int.toHex(): String = toHexString(HexFormat {
    upperCase = true
    number {
        removeLeadingZeros = true
        minLength = 2
    }
})
