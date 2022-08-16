@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.state.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * Selection and selection-based configurations.
 */
class StateSelection(
    val onViewClick: () -> Unit,
) : BaseSelection()