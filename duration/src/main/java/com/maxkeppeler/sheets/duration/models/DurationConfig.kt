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
package com.maxkeppeler.sheets.duration.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * The general configuration for the duration dialog.
 * @param timeFormat Available color selection modes. If null, both are used.
 * @param currentTime Current time in seconds.
 * @param minTime Minimum time.
 * @param maxTime Maximum time.
 * @param displayClearButton Replaces the "00" Value-Button with a button to clear all values.
 */
data class DurationConfig(
    val timeFormat: DurationFormat = DurationFormat.MM_SS,
    val currentTime: Long? = null,
    val minTime: Long = 0,
    val maxTime: Long = Long.MAX_VALUE,
    val displayClearButton: Boolean = false
) : BaseConfigs()