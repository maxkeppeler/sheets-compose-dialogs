package com.maxkeppeler.sheets.clock.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import java.time.LocalTime

/**
 * Available calendar configurations.
 */
data class ClockTimeConfig(

    /**
     * Current time.
     */
    val currentTime: LocalTime? = null,

    /**
     *
     */
    val is24HourFormat: Boolean? = null,

    ) : BaseConfigs()