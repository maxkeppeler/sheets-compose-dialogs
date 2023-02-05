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
package com.maxkeppeler.sheets.clock.models

import com.maxkeppeker.sheets.core.icons.LibIcons
import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeker.sheets.core.utils.BaseConstants
import java.time.LocalTime

/**
 * The general configuration for the clock dialog.
 * @param defaultTime The default time.
 * @param boundary Optional [ClosedRange] of [LocalTime] representing the time boundary
 * @param is24HourFormat If the 24HourFormat is enabled.
 * @param icons The style of icons that are used for dialog/ view-specific icons.
 * @param orientation The orientation of the view or null for auto orientation.
 */
data class ClockConfig(
    val defaultTime: LocalTime? = null,
    val boundary: ClosedRange<LocalTime>? = null,
    val is24HourFormat: Boolean? = null,
    override val icons: LibIcons = BaseConstants.DEFAULT_ICON_STYLE,
    override val orientation: LibOrientation? = BaseConstants.DEFAULT_LIB_LAYOUT,
) : BaseConfigs()