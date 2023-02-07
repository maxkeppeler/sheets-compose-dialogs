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

import androidx.compose.runtime.Composable

/**
 * Represents a custom view.
 * @param view The view that will be added.
 * @param header The additional [InputHeader] to add more context information to the selection.
 * @param columns The columns that this input spans.
 */
class InputCustomView(
    internal val view: @Composable () -> Unit,
    override val header: InputHeader? = null,
    override val columns: Int? = null,
) : Input() {

    override fun isInput() = false
}