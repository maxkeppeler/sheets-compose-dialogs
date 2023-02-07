/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.option.models

import androidx.annotation.IntRange
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.utils.BaseConstants

/**
 * The selection configuration for the list dialog.
 * @param options The options that will be displayed.
 */
sealed class OptionSelection(
    open val options: List<Option> = listOf()
) : BaseSelection() {

    /**
     * Single-choice selection for the list dialog.
     * @param options The options that will be displayed.
     * @param withButtonView Show the dialog with the buttons view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param onSelectOption The listener that returns the selected index and the selected option when the positive button is clicked.
     */
    class Single(
        override val options: List<Option>,
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON,
        val onSelectOption: (index: Int, option: Option) -> Unit
    ) : OptionSelection()

    /**
     * Multiple-choice selection for the list dialog.
     * @param options The options that will be displayed.
     * @param minChoices The minimum amount of choices that are allowed.
     * @param maxChoices The maximum amount of choices that are allowed.
     * @param maxChoicesStrict Allow the user to temporarily select more options than maximum choices.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param onSelectOptions The listener that returns the selected indices and the selected options when the positive button is clicked.
     */
    class Multiple(
        override val options: List<Option>,
        @IntRange(from = 1L, to = 90L) val minChoices: Int? = null,
        @IntRange(from = 3L, to = 90L) val maxChoices: Int? = null,
        val maxChoicesStrict: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON,
        val onSelectOptions: (selectedIndices: List<Int>, selectedOptions: List<Option>) -> Unit
    ) : OptionSelection()
}