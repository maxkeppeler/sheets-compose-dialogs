@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.clock_time.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * Selection and selection-based configurations.
 */
sealed class ClockTimeSelection: BaseSelection() {

    /**
     * Select a time.
     */
    class HoursMinutes(
        override val withButtonView: Boolean = false,
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val onPositiveClick: (hour: Int, minute: Int) -> Unit,
    ) : ClockTimeSelection()

    /**
     * Select a time with seconds.
     */
    class HoursMinutesSeconds(
        override val withButtonView: Boolean = false,
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val onPositiveClick: (hour: Int, minute: Int, seconds: Int) -> Unit,
    ) : ClockTimeSelection()

}
