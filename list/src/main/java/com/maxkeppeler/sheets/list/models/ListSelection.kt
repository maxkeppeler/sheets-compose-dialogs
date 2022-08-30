@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.list.models

import androidx.annotation.IntRange
import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * Available selection modes and selection-based configurations.
 */
sealed class ListSelection(
    open val options: List<ListOption>
) : BaseSelection() {

    /**
     * Select a single list option.
     */
    class Single(
        override val options: List<ListOption>,
        val showRadioButtons: Boolean = false,
        override val withButtonView: Boolean = true,
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val onSelectOption: (index: Int, option: ListOption) -> Unit
    ) : ListSelection(options = options)

    /**
     * Select multiple options.
     */
    class Multiple(
        override val options: List<ListOption>,
        val showCheckBoxes: Boolean = false,
        @IntRange(from = 1L, to = 90L) val minChoices: Int? = null,
        @IntRange(from = 3L, to = 90L) val maxChoices: Int? = null,
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val onSelectOptions: (selectedIndices: List<Int>, selectedOptions: List<ListOption>) -> Unit
    ) : ListSelection(options = options)
}