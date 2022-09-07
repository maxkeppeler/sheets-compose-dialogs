@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeker.sheets.core.models

import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * The selection configuration for the core dialog.
 */
class CoreSelection(
    override val withButtonView: Boolean = true,
    override val negativeButton: SelectionButton? = null,
    override val onNegativeClick: (() -> Unit)? = null,
    override val positiveButton: SelectionButton? = null,
    val onPositiveClick: (() -> Unit)? = null,
) : BaseSelection()