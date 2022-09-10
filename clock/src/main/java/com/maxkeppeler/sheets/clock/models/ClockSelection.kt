@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.clock.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton

/**
 * The selection configuration for the clock dialog.
 */
sealed class ClockSelection : BaseSelection() {

    /**
     * Select a time with hours and minutes.
     * @param withButtonView Show the dialog with the buttons view.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param onPositiveClick The listener that returns the selected hours and minutes.
     */
    class HoursMinutes(
        override val withButtonView: Boolean = false,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        val onPositiveClick: (hours: Int, minutes: Int) -> Unit,
    ) : ClockSelection()

    /**
     * Select a time with hours, minutes and seconds.
     * @param withButtonView Show the dialog with the buttons view.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param onPositiveClick The listener that returns the selected hours, minutes and seconds.
     */
    class HoursMinutesSeconds(
        override val withButtonView: Boolean = false,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        val onPositiveClick: (hours: Int, minutes: Int, seconds: Int) -> Unit,
    ) : ClockSelection()
}
