package com.maxkeppeler.sheets.time.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * Available calendar configurations.
 */
data class TimeConfig(

    /**
     * Available color selection modes. If null, both are used.
     */
    val timeFormat: TimeFormat = TimeFormat.MM_SS,

    /**
     * Current time.
     */
    val currentTime: Long? = null,

    /**
     * Minimum time.
     */
    val minTime: Long = 0,

    /**
     * Max time.
     */
    val maxTime: Long = Long.MAX_VALUE,

    /**
     * Replaces the "00" Value-Button with a button to clear all values.
     */
    val displayClearButton: Boolean = false

) : BaseConfigs()