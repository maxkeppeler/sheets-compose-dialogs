@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.time.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * Selection and selection-based configurations.
 */
class TimeSelection(
    override val negativeButtonText: String? = null,
    override val onNegativeClick: (() -> Unit)? = null,
    override val positiveButtonText: String? = null,
    val onPositiveClick: () -> Unit,
) : BaseSelection()
