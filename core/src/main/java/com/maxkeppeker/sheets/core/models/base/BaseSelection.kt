package com.maxkeppeker.sheets.core.models.base

/**
 * Base selection for dialog-specific selections.
 */
abstract class BaseSelection {
    open val withButtonView: Boolean = true
    open val negativeButton: SelectionButton? = null
    open val positiveButton: SelectionButton? = null
    open val onNegativeClick: (() -> Unit)? = null
}