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
package com.maxkeppeler.sheets.date_time.models

import com.maxkeppeker.sheets.core.icons.LibIcons
import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeker.sheets.core.utils.BaseConstants.DEFAULT_ICON_STYLE
import com.maxkeppeler.sheets.date_time.utils.Constants

/**
 * The general configuration for the date time dialog.
 * @param hideDateCharacters Hide all characters that can appear alongside the date relevant values.
 * @param hideTimeCharacters Hide all characters that can appear alongside the time relevant values.
 * @param minYear The minimum year that is selectable.
 * @param maxYear The maximum year that is selectable.
 * @param icons The style of icons that are used for dialog/ view-specific icons.
 */
class DateTimeConfig(
    val hideDateCharacters: Boolean = false,
    val hideTimeCharacters: Boolean = false,
    val minYear: Int = Constants.DEFAULT_MIN_YEAR,
    val maxYear: Int = Constants.DEFAULT_MAX_YEAR,
    override val icons: LibIcons = DEFAULT_ICON_STYLE,
) : BaseConfigs()