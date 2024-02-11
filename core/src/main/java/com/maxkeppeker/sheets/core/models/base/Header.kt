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
package com.maxkeppeker.sheets.core.models.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

/**
 * Defined implementations of a header for the dialogs.
 */
abstract class Header {

    /**
     * Standard implementation of a header.
     * @param title The title of the header.
     * @param icon The icon of the header.
     */
    data class Default(
        val title: String,
        val icon: IconSource? = null,
    ) : Header()

    /**
     * Custom implementation of a header.
     * @param header The custom header implementation with the horizontal padding values of the default header.
     */
    data class Custom(
        val header: @Composable (paddingValues: PaddingValues) -> Unit
    ) : Header()
}