package com.maxkeppeler.sheets.duration.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * The general configuration for the duration dialog.
 * @param timeFormat Available color selection modes. If null, both are used.
 * @param currentTime Current time in seconds.
 * @param minTime Minimum time.
 * @param maxTime Maximum time.
 * @param displayClearButton Replaces the "00" Value-Button with a button to clear all values.
 */
data class DurationConfig(
    val timeFormat: DurationFormat = DurationFormat.MM_SS,
    val currentTime: Long? = null,
    val minTime: Long = 0,
    val maxTime: Long = Long.MAX_VALUE,
    val displayClearButton: Boolean = false
) : BaseConfigs()