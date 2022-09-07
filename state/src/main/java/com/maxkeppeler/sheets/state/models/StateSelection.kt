@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.state.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * The selection configuration for the state dialog.
 * @param onViewClick The listener that is invoked when the dialog is clicked.
 */
class StateSelection(
    val onViewClick: () -> Unit,
) : BaseSelection()