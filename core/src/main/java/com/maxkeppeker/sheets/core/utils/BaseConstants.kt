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
package com.maxkeppeker.sheets.core.utils

import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.icons.LibIcons
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeker.sheets.core.models.base.SelectionButton

/**
 * Defines module-wide constants.
 */
object BaseConstants {

    // Behaviours

    const val SUCCESS_DISMISS_DELAY = 600L

    val DEFAULT_ICON_STYLE = LibIcons.Rounded
    val DEFAULT_LIB_LAYOUT: LibOrientation? = null // Auto orientation

    val KEYBOARD_HEIGHT_MAX = 300.dp
    const val KEYBOARD_RATIO = 0.8f

    val DYNAMIC_SIZE_MAX = 200.dp

    val DEFAULT_NEGATIVE_BUTTON = SelectionButton(
        textRes = android.R.string.cancel,
        type = ButtonStyle.TEXT
    )

    val DEFAULT_POSITIVE_BUTTON =
        SelectionButton(
            textRes = android.R.string.ok,
            type = ButtonStyle.TEXT
        )
}