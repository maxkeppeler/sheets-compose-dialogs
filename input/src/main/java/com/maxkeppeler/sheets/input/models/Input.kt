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
@file:Suppress("unused")

package com.maxkeppeler.sheets.input.models

import android.os.Bundle
import java.io.Serializable

/**
 * Base input definition that can be added to the input dialog.
 * @param key The key of the input that is used for saving the data in the result bundle. (Alternatively the index of the input is used.)
 * @param required If the input is required to finish the dialog.
 * @param header The additional [InputHeader] to add more context information to the selection.
 * @param columns The columns that this input spans.
 */
abstract class Input(
    internal open val key: String? = null,
    internal open val required: Boolean = false,
    internal open val header: InputHeader? = null,
    internal open val columns: Int? = null,
) : Serializable {

    internal var position: Int = 0

    internal var valid: Boolean = false

    /**
     * Defines if the input allows interaction and selection, or not.
     */
    internal open fun isInput(): Boolean = true

    /**
     * Check if the input value is valid.
     */
    internal open fun isValid(): Boolean = true

    /**
     * Invoke the change listener that returns the changed value.
     */
    internal open fun onChange(): Unit? = Unit

    /**
     * Invoke the result listener that returns the result value.
     */
    internal open fun onResult(): Unit? = Unit

    /**
     * Save the input value into the bundle.
     * Takes the index as an key, if there's no unique input key available.
     * @param bundle The bundle where the input data is saved.
     */
    internal open fun putValue(bundle: Bundle): Unit? = Unit

    /**
     * Helper method that either uses the key or the index as String to assign key-value pairs
     * Returns the key that is used to store input data in the bundle.
     * @return The actual key that can be used to store input data in the bundle.
     */
    internal open fun getBundleKey(): String =
        if (key.isNullOrEmpty()) position.toString() else key!!
}

