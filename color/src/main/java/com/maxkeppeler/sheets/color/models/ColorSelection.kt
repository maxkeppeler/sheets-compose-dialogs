@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.color.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * Selection and selection-based configurations.
 */
class ColorSelection(
    val selectedColor: SelectedColor? = null,
    override val negativeButtonText: String? = null,
    override val positiveButtonText: String? = null,
    val onSelectNone: (() -> Unit)? = null,
    val onSelectColor: (color: Int) -> Unit,
) : BaseSelection()