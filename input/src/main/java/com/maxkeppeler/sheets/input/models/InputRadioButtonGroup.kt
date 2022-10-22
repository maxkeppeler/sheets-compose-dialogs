/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
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
@file:Suppress("unused")

package com.maxkeppeler.sheets.input.models

import android.os.Bundle

/**
 * Represents a group of radio buttons.
 * @param items A list of Strings representing the individual radio button text.
 * @param selectedIndex The index of the item that is selected.
 * @param changeListener The listener that returns the changed value.
 * @param resultListener The listener that returns the final value.
 * @param key The key of the input that is used for saving the data in the result bundle. (Alternatively the index of the input is used.)
 * @param required If the input is required to finish the dialog.
 * @param header The additional [InputHeader] to add more context information to the selection.
 * @param columns The columns that this input spans.
 */
class InputRadioButtonGroup(
    internal val items: List<String>,
    private val selectedIndex: Int? = null,
    private val changeListener: ((Int) -> Unit)? = null,
    private val resultListener: ((Int?) -> Unit)? = null,
    override val key: String? = null,
    override val required: Boolean = false,
    override val header: InputHeader? = null,
    override val columns: Int? = null,
) : Input() {

    internal var value: Int? = selectedIndex
        set(value) {
            if (field != value) {
                field = value
                onChange()
            }
            valid = isValid()
        }

    override fun onChange() = changeListener?.invoke(value!!)

    override fun onResult() = resultListener?.invoke(value)

    override fun isValid(): Boolean = required && value != null || !required

    override fun putValue(bundle: Bundle) = value?.let { bundle.putInt(getBundleKey(), it) }

}