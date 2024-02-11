/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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
@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.number.models

import androidx.annotation.IntRange
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.utils.BaseConstants

/**
 * The selection configuration for the number dialog.
 */
sealed class NumberSelection : BaseSelection() {

    /**
     * Selection for an integer number.
     * @param withButtonView If the button view should be displayed.
     * @param extraButton An extra button to be displayed.
     * @param onExtraButtonClick Callback when the extra button is clicked.
     * @param negativeButton The negative button to be displayed.
     * @param onNegativeClick Callback when the negative button is clicked.
     * @param positiveButton The positive button to be displayed.
     * @param defaultValue The default value.
     * @param minValue The minimum value.
     * @param maxValue The maximum value.
     * @param onPositiveClick Callback when the positive button is clicked with the selected integer value.
     */
    class Integer(
        override val withButtonView: Boolean = false,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON,
        val defaultValue: Int? = null,
        val minValue: Int = 0,
        val maxValue: Int = 100,
        val onPositiveClick: (value: Int) -> Unit,
    ) : NumberSelection()

    /**
     * Selection for a decimal number.
     * @param withButtonView If the button view should be displayed.
     * @param extraButton An extra button to be displayed.
     * @param onExtraButtonClick Callback when the extra button is clicked.
     * @param negativeButton The negative button to be displayed.
     * @param onNegativeClick Callback when the negative button is clicked.
     * @param positiveButton The positive button to be displayed.
     * @param defaultValue The default value.
     * @param minValue The minimum value.
     * @param maxValue The maximum value.
     * @param decimalPlaces The number of decimal places to be displayed.
     * @param onPositiveClick Callback when the positive button is clicked with the selected decimal value.
     */
    class Decimal(
        override val withButtonView: Boolean = false,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON,
        val defaultValue: Long? = null,
        val minValue: Long = 0,
        val maxValue: Long = 100,
        @IntRange(from = 1L, to = 4L)
        val decimalPlaces: Int = 2,
        val onPositiveClick: (value: Long) -> Unit,
    ) : NumberSelection()
}
