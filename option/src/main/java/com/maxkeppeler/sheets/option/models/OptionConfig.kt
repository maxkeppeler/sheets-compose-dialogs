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
package com.maxkeppeler.sheets.option.models

import androidx.annotation.IntRange
import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeler.sheets.option.utils.Constants.GRID_COLUMNS_DEFAULT

/**
 * The general configuration for the option dialog.
 * @param mode The mode that is used to display the options.
 * @param gridColumns The amount of columns when display mode is [DisplayMode.GRID_VERTICAL].
 */
class OptionConfig(
    val mode: DisplayMode = DisplayMode.GRID_VERTICAL,
    @IntRange(from = 1, to = 5) val gridColumns: Int = GRID_COLUMNS_DEFAULT
) : BaseConfigs()