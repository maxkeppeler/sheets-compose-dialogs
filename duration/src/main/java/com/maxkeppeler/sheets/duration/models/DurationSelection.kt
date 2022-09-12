@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.duration.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton

/**
 * The selection configuration for the duration dialog.
 * @param extraButton An extra button that can be used for a custom action.
 * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
 * @param negativeButton The button that will be used as a negative button.
 * @param onNegativeClick The listener that is invoked when the negative button is clicked.
 * @param positiveButton The button that will be used as a positive button.
 * @param onPositiveClick The listener that returns the selected duration time in seconds.
 */
class DurationSelection(
    override val extraButton: SelectionButton? = null,
    override val onExtraButtonClick: (() -> Unit)? = null,
    override val negativeButton: SelectionButton? = null,
    override val onNegativeClick: (() -> Unit)? = null,
    override val positiveButton: SelectionButton? = null,
    val onPositiveClick: (time: Long) -> Unit,
) : BaseSelection()
