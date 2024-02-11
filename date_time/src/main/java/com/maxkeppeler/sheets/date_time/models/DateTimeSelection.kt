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
package com.maxkeppeler.sheets.date_time.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.utils.BaseConstants
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.FormatStyle

/**
 * The selection configuration for the date time dialog.
 * @param dateFormatStyle The style of the date format.
 * @param timeFormatStyle The style of the time format.
 */
sealed class DateTimeSelection(
    open val dateFormatStyle: FormatStyle? = null,
    open val timeFormatStyle: FormatStyle? = null,
) : BaseSelection() {

    /**
     * Select a date.
     * @param withButtonView If true, the dialog will be shown with a button view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param dateFormatStyle The style of the date format.
     * @param selectedDate The selected date.
     * @param onPositiveClick The listener that returns the selected date.
     */
    data class Date(
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON,
        override val dateFormatStyle: FormatStyle = FormatStyle.MEDIUM,
        val selectedDate: LocalDate? = null,
        val onPositiveClick: (LocalDate) -> Unit,
    ) : DateTimeSelection()


    /**
     * Select a time.
     * @param withButtonView If true, the dialog will be shown with a button view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param timeFormatStyle The style of the time format.
     * @param selectedTime The selected time.
     * @param onPositiveClick The listener that returns the selected date.
     */
    data class Time(
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON,
        override val timeFormatStyle: FormatStyle = FormatStyle.SHORT,
        val selectedTime: LocalTime? = null,
        val onPositiveClick: (LocalTime) -> Unit,
    ) : DateTimeSelection()

    /**
     * Select a date & time.
     * @param withButtonView If true, the dialog will be shown with a button view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param dateFormatStyle The style of the date format.
     * @param timeFormatStyle The style of the time format.
     * @param selectedTime The selected time.
     * @param selectedDate The selected date.
     * @param startWithTime If the time selection should be shown before the date selection.
     * @param onPositiveClick The listener that returns the selected date-time.
     */
    data class DateTime(
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON,
        override val dateFormatStyle: FormatStyle = FormatStyle.MEDIUM,
        override val timeFormatStyle: FormatStyle = FormatStyle.SHORT,
        val selectedTime: LocalTime? = null,
        val selectedDate: LocalDate? = null,
        val startWithTime: Boolean = false,
        val onPositiveClick: (LocalDateTime) -> Unit,
    ) : DateTimeSelection()

}