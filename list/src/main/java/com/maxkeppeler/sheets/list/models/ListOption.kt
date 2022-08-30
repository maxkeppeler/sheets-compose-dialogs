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

import com.maxkeppeker.sheets.core.models.ImageSource
import java.io.Serializable
import java.util.*

/**
 * An option is represented with at least a text.
 * A drawable is optional but makes it easier to understand to the user.
 */
data class ListOption(

    /**
     * The icon displayed in the option.
     */
    val icon: ImageSource? = null,

    /**
     * The title displayed in the option.
     */
    val titleText: String,

    /**
     * The subtitle displayed in the option.
     */
    val subtitleText: String? = null,

    /**
     * The state if the option is selected by default.
     */
    val selected: Boolean = false,

    /**
     * The unique key for the option.
     */
    val key: String? = UUID.randomUUID().toString(),

    ) : Serializable

