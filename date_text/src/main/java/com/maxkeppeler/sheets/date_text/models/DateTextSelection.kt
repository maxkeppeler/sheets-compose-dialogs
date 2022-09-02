@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.date_text.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.FormatStyle
import java.util.*

/**
 * Selection and selection-based configurations.
 */
sealed class DateTextSelection(
    override val negativeButtonText: String? = null,
    override val onNegativeClick: (() -> Unit)? = null,
    override val positiveButtonText: String? = null,
    open val locale: Locale = Locale.getDefault(),
    open val dateFormatStyle: FormatStyle? = null,
    open val timeFormatStyle: FormatStyle? = null,
) : BaseSelection() {

    data class Date(
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        override val withButtonView: Boolean = true,
        override val locale: Locale = Locale.getDefault(),
        override val dateFormatStyle: FormatStyle = FormatStyle.MEDIUM,
        val onPositiveClick: (LocalDate) -> Unit,
    ) : DateTextSelection()

    data class Time(
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        override val withButtonView: Boolean = true,
        override val locale: Locale = Locale.getDefault(),
        override val timeFormatStyle: FormatStyle = FormatStyle.SHORT,
        val onPositiveClick: (LocalTime) -> Unit,
    ) : DateTextSelection()

    data class DateTime(
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        override val withButtonView: Boolean = true,
        override val locale: Locale = Locale.getDefault(),
        override val dateFormatStyle: FormatStyle = FormatStyle.MEDIUM,
        override val timeFormatStyle: FormatStyle = FormatStyle.SHORT,
        val startWithTime: Boolean = false,
        val onPositiveClick: (LocalDateTime) -> Unit,
    ) : DateTextSelection()

}