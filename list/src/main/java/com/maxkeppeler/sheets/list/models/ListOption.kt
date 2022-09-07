/*
 *  Copyright (C) 2020. Maximilian Keppeler (https://www.maxkeppeler.com)
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

package com.maxkeppeler.sheets.list.models

import com.maxkeppeker.sheets.core.models.base.IconSource
import java.io.Serializable
import java.util.*

/**
 * An option for the the list dialog.
 * @param icon The icon displayed in the option.
 * @param titleText The title displayed in the option.
 * @param subtitleText The subtitle displayed in the option.
 * @param selected The state if the option is selected by default.
 */
data class ListOption(
    val icon: IconSource? = null,
    val titleText: String,
    val subtitleText: String? = null,
    val selected: Boolean = false,
) : Serializable {
    internal var position: Int = 0
}

