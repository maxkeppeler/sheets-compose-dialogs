package com.maxkeppeler.sheets.date_time.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeler.sheets.date_time.utils.Constants

/**
 * The general configuration for the date time dialog.
 * @param hideDateCharacters Hide all characters that can appear alongside the date relevant values.
 * @param hideTimeCharacters Hide all characters that can appear alongside the time relevant values.
 * @param minYear The minimum year that is selectable.
 * @param maxYear The maximum year that is selectable.
 */
class DateTimeConfig(
    val hideDateCharacters: Boolean = false,
    val hideTimeCharacters: Boolean = false,
    val minYear: Int = Constants.DEFAULT_MIN_YEAR,
    val maxYear: Int = Constants.DEFAULT_MAX_YEAR,
) : BaseConfigs()