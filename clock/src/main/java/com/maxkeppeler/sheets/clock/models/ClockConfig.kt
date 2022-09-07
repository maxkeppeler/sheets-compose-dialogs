package com.maxkeppeler.sheets.clock.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import java.time.LocalTime

/**
 * The general configuration for the clock dialog.
 * @param defaultTime The default time.
 * @param is24HourFormat If the 24HourFormat is enabled.
 */
data class ClockConfig(
    val defaultTime: LocalTime? = null,
    val is24HourFormat: Boolean? = null,
) : BaseConfigs()