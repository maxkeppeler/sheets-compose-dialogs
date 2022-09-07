@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.color.models

import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * The selection configuration for the color dialog.
 * @param selectedColor A color that is selected by default.
 * @param negativeButton The button that will be used as a negative button.
 * @param onNegativeClick The listener that is invoked when the negative button is clicked.
 * @param positiveButton The button that will be used as a positive button.
 * @param onSelectNone The listener that is invoked when no color is selected.
 * @param onSelectColor The listener that returns the selected color.
 */
class ColorSelection(
    val selectedColor: SingleColor? = null,
    override val negativeButton: SelectionButton? = null,
    override val onNegativeClick: (() -> Unit)? = null,
    override val positiveButton: SelectionButton? = null,
    val onSelectNone: (() -> Unit)? = null,
    val onSelectColor: (color: Int) -> Unit,
) : BaseSelection()