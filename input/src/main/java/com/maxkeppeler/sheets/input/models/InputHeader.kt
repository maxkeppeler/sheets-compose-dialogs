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

import com.maxkeppeker.sheets.core.models.base.IconSource
import java.io.Serializable

/**
 * Represents a header that can be added to invidual input types.
 * @param title The text that is used for the title.
 * @param body The text that is used for the body.
 * @param icon The icon that is displayed on the left side of the title.
 */
class InputHeader(
    val title: String? = null,
    val body: String? = null,
    val icon: IconSource? = null,
) : Serializable
