@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.calendar.models

import android.util.Range
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import java.time.LocalDate

/**
 * Available selection modes and selection-based configurations.
 */
sealed class CalendarSelection(
    val text: Boolean = true,
) : BaseSelection() {

    /**
     * Select a date.
     */
    class Date(
        override val withButtonView: Boolean = false,
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val selectedDate: LocalDate? = null,
        val onSelectDate: (date: LocalDate) -> Unit
    ) : CalendarSelection()

    /**
     * Select multiple dates.
     */
    class Dates(
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val selectedDates: List<LocalDate>? = null,
        val onSelectDate: (dates: List<LocalDate>) -> Unit
    ) : CalendarSelection()

    /**
     * Select a range (start and end date).
     */
    class Period(
        override val withButtonView: Boolean = false,
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val selectedRange: Range<LocalDate>? = null,
        val onSelectRange: (startDate: LocalDate, endDate: LocalDate) -> Unit
    ) : CalendarSelection()
}