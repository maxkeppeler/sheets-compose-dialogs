@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.info.models

import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * The selection configuration for the info dialog.
 * @param extraButton An extra button that can be used for a custom action.
 * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
 * @param negativeButton The button that will be used as a negative button.
 * @param onNegativeClick The listener that is invoked when the negative button is clicked.
 * @param positiveButton The button that will be used as a positive button.
 * @param onPositiveClick The listener that is invoked when the positive button is clicked.
 */
class InfoSelection(
    override val extraButton: SelectionButton? = null,
    override val onExtraButtonClick: (() -> Unit)? = null,
    override val negativeButton: SelectionButton? = null,
    override val onNegativeClick: (() -> Unit)? = null,
    override val positiveButton: SelectionButton? = null,
    val onPositiveClick: () -> Unit,
) : BaseSelection()