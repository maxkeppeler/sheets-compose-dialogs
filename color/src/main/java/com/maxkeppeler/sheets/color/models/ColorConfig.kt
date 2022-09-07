package com.maxkeppeler.sheets.color.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * The general configuration for the clock dialog.
 * @param displayMode Available color selection modes. If null, both are used.
 * @param defaultDisplayMode Default view when opening ColorDialog.
 * @param templateColors Colors for the [ColorSelectionMode.TEMPLATE]-view.
 * @param allowCustomColorAlphaValues Allow alpha values in the custom color picker.
 */
data class ColorConfig(
    val displayMode: ColorSelectionMode? = null,
    val defaultDisplayMode: ColorSelectionMode? = null,
    val templateColors: MultipleColors = MultipleColors.ColorsInt(),
    val allowCustomColorAlphaValues: Boolean = true,
) : BaseConfigs()