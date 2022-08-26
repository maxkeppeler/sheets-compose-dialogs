package com.maxkeppeler.sheets.option.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * Available calendar configurations.
 */
class OptionConfig(

    /**
     * Style of option dialog.
     */
    val style: DisplayMode = DisplayMode.GRID_VERTICAL,

    val gridColumns: Int? = null

// TODO: config info button drawable,
// TODO: config position preView and customView out of bounds

) : BaseConfigs()