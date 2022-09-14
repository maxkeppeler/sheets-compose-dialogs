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
package com.maxkeppeler.sheets.duration.views

import androidx.compose.runtime.Composable

/**
 * The main component that includes the time view and an info view,
 * when the selected time is out of the bounds of the configuration.
 * @param indexOfFirstValue The index of the first valid value.
 * @param valuePairs The list of value pairs that will be displayed.
 * @param minTimeValue The minimum valid time.
 * @param maxTimeValue The maximum valid time.
 */
@Composable
internal fun TimeDisplayComponent(
    indexOfFirstValue: Int?,
    valuePairs: List<Pair<String, String>>,
    minTimeValue: Long?,
    maxTimeValue: Long?
) {

    TimeValueComponent(
        indexOfFirstValue = indexOfFirstValue,
        valuePairs = valuePairs
    )

    TimeInfoComponent(
        minTimeValue = minTimeValue,
        maxTimeValue = maxTimeValue
    )
}



