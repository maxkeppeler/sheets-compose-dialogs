package com.maxkeppeler.sheets.date_time.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeler.sheets.date_time.utils.Constants

/**
 * Available date time configurations.
 */
class DateTextConfig(

    /**
     * Hide characters of localized date pattern.
     */
    val hideDateCharacters: Boolean = false,

    /**
     * Hide characters of localized time pattern / common clock time separator colon ":".
     */
    val hideTimeCharacters: Boolean = false,

    /**
     * Min-selectable year.
     */
    val minYear: Int = Constants.DEFAULT_MIN_YEAR,

    /**
     * Max-selectable year.
     */
    val maxYear: Int = Constants.DEFAULT_MAX_YEAR,

) : BaseConfigs()