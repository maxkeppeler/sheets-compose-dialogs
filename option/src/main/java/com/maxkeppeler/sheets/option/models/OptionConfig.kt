package com.maxkeppeler.sheets.option.models

import androidx.annotation.IntRange
import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeler.sheets.option.utils.Constants.GRID_COLUMNS_DEFAULT

/**
 * The general configuration for the option dialog.
 * @param mode The mode that is used to display the options.
 * @param gridColumns The amount of columns when display mode is [DisplayMode.GRID_VERTICAL].
 */
class OptionConfig(
    val mode: DisplayMode = DisplayMode.GRID_VERTICAL,
    @IntRange(from = 1, to = 5) val gridColumns: Int = GRID_COLUMNS_DEFAULT
) : BaseConfigs()