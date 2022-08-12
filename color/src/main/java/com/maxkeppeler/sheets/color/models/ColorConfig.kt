package com.maxkeppeler.sheets.color.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * Available calendar configurations.
 */
data class ColorConfig(

    /**
     * Available color selection modes. If null, both are used.
     */
    val displayMode: ColorSelectionMode? = null,

    /**
     * Default view when opening ColorDialog.
     */
    val defaultDisplayMode: ColorSelectionMode? = null,

    /**
     * Colors for the [ColorSelectionMode.TEMPLATE]-view.
     */
    val templateColors: TemplateColors = TemplateColors.ColorsInt(),

    /**
     * Allow
     */
    val allowCustomColorAlphaValues: Boolean = true,

    ) : BaseConfigs()