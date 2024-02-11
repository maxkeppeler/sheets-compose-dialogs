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
package com.maxkeppeler.sheets.info.models

import androidx.compose.runtime.Composable

/**
 * Defined implementations of a body for the info dialog.
 */
abstract class InfoBody {

    /**
     * Standard implementation of a body.
     * @param bodyText Text that will set as the title
     * @param preBody Content that is added before the body text.
     * @param postBody Content that is added after the body text.
     */
    data class Default(
        val bodyText: String,
        val preBody: @Composable () -> Unit = {},
        val postBody: @Composable () -> Unit = {}
    ) : InfoBody()

    /**
     * Custom implementation of a body.
     */
    data class Custom(
        val body: @Composable () -> Unit
    ) : InfoBody()
}