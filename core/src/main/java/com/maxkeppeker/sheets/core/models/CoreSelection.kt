/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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

package com.maxkeppeker.sheets.core.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.utils.BaseConstants

/**
 * The selection configuration for the core dialog.
 * @param withButtonView Whether the button view should be shown or not.
 * @param extraButton An extra button that can be used for a custom action.
 * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
 * @param negativeButton The button that will be used as a negative button.
 * @param onNegativeClick The listener that is invoked when the negative button is clicked.
 * @param positiveButton The button that will be used as a positive button.
 * @param onPositiveClick The listener that is invoked when the positive button is clicked.
 */
class CoreSelection(
    override val withButtonView: Boolean = true,
    override val extraButton: SelectionButton? = null,
    override val onExtraButtonClick: (() -> Unit)? = null,
    override val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON,
    override val onNegativeClick: (() -> Unit)? = null,
    override val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON,
    val onPositiveClick: (() -> Unit)? = null,
) : BaseSelection()