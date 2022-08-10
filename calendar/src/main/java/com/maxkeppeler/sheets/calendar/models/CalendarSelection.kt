@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.calendar.models

import android.util.Range
import java.time.LocalDate

/**
 * Available selection modes and selection-based configurations.
 */
sealed class CalendarSelection(
    open val withButtonView: Boolean = true,
    open val negativeButtonText: String? = null,
    open val positiveButtonText: String? = null,
) {

    /**
     * Select a date.
     */
    class Date(
        override val withButtonView: Boolean = false,
        override val negativeButtonText: String? = null,
        override val positiveButtonText: String? = null,
        val selectedDate: LocalDate? = null,
        val onSelectDate: (date: LocalDate) -> Unit
    ) : CalendarSelection(
        withButtonView = withButtonView,
        negativeButtonText = negativeButtonText,
        positiveButtonText = positiveButtonText,
    )

    /**
     * Select multiple dates.
     */
    class Dates(
        override val negativeButtonText: String? = null,
        override val positiveButtonText: String? = null,
        val selectedDates: List<LocalDate>? = null,
        val onSelectDate: (dates: List<LocalDate>) -> Unit
    ) : CalendarSelection(
        negativeButtonText = negativeButtonText,
        positiveButtonText = positiveButtonText
    )

    /**
     * Select a range (start and end date).
     */
    class Period(
        override val withButtonView: Boolean = false,
        override val negativeButtonText: String? = null,
        override val positiveButtonText: String? = null,
        val selectedRange: Range<LocalDate>? = null,
        val onSelectRange: (startDate: LocalDate, endDate: LocalDate) -> Unit
    ) : CalendarSelection(withButtonView)
}