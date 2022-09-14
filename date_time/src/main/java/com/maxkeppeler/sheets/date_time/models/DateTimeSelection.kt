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
package com.maxkeppeler.sheets.date_time.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.FormatStyle
import java.util.*

/**
 * The selection configuration for the date time dialog.
 * @param locale The locale that is used for the date and time format.
 * @param dateFormatStyle The style of the date format.
 * @param timeFormatStyle The style of the time format.
 */
sealed class DateTimeSelection(
    open val locale: Locale = Locale.getDefault(),
    open val dateFormatStyle: FormatStyle? = null,
    open val timeFormatStyle: FormatStyle? = null,
) : BaseSelection() {

    /**
     * Select a date.
     * @param withButtonView Show the dialog with the buttons view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param locale The locale that is used for the date and time format.
     * @param dateFormatStyle The style of the date format.
     * @param onPositiveClick The listener that returns the selected date.
     */
    data class Date(
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        override val locale: Locale = Locale.getDefault(),
        override val dateFormatStyle: FormatStyle = FormatStyle.MEDIUM,
        val onPositiveClick: (LocalDate) -> Unit,
    ) : DateTimeSelection()


    /**
     * Select a time.
     * @param withButtonView Show the dialog with the buttons view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param locale The locale that is used for the date and time format.
     * @param timeFormatStyle The style of the time format.
     * @param onPositiveClick The listener that returns the selected date.
     */
    data class Time(
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        override val locale: Locale = Locale.getDefault(),
        override val timeFormatStyle: FormatStyle = FormatStyle.SHORT,
        val onPositiveClick: (LocalTime) -> Unit,
    ) : DateTimeSelection()

    /**
     * Select a date & time.
     * @param withButtonView Show the dialog with the buttons view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param locale The locale that is used for the date and time format.
     * @param dateFormatStyle The style of the date format.
     * @param timeFormatStyle The style of the time format.
     * @param startWithTime If the time selection should be shown before the date selection.
     * @param onPositiveClick The listener that returns the selected date-time.
     */
    data class DateTime(
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        override val locale: Locale = Locale.getDefault(),
        override val dateFormatStyle: FormatStyle = FormatStyle.MEDIUM,
        override val timeFormatStyle: FormatStyle = FormatStyle.SHORT,
        val startWithTime: Boolean = false,
        val onPositiveClick: (LocalDateTime) -> Unit,
    ) : DateTimeSelection()

}