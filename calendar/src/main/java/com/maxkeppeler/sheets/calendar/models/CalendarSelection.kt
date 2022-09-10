@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.calendar.models

import android.util.Range
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import java.time.LocalDate

/**
 * The selection configuration for the calendar dialog.
 */
sealed class CalendarSelection : BaseSelection() {

    /**
     * Select a date.
     * @param withButtonView Show the dialog with the buttons view.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param selectedDate The date that is selected by default.
     * @param onSelectDate The listener that returns the selected date.
     */
    class Date(
        override val withButtonView: Boolean = true,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        val selectedDate: LocalDate? = null,
        val onSelectDate: (date: LocalDate) -> Unit
    ) : CalendarSelection()

    /**
     * Select multiple dates.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param selectedDates A list of dates that is selected by default.
     * @param onSelectDates The listener that returns the selected dates.
     */
    class Dates(
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        val selectedDates: List<LocalDate>? = null,
        val onSelectDates: (dates: List<LocalDate>) -> Unit
    ) : CalendarSelection()

    /**
     * Select a range (start and end date).
     * @param withButtonView Show the dialog with the buttons view.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param selectedRange The range that is selected by default.
     * @param onSelectRange The listener that returns the selected range.
     */
    class Period(
        override val withButtonView: Boolean = true,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        val selectedRange: Range<LocalDate>? = null,
        val onSelectRange: (startDate: LocalDate, endDate: LocalDate) -> Unit
    ) : CalendarSelection()
}