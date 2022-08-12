@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.info.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * Selection and selection-based configurations.
 */
class InfoSelection(
    override val negativeButtonText: String? = null,
    override val onNegativeClick: (() -> Unit)? = null,
    override val positiveButtonText: String? = null,
    val onPositiveClick: () -> Unit,
) : BaseSelection()