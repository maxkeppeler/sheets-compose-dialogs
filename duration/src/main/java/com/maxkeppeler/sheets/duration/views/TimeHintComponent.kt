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


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maxkeppeler.sheets.duration.R
import com.maxkeppeler.sheets.duration.utils.getFormattedHintTime

/**
 * The info component that will show a hint if the selected time is out of the specified bounds.
 * @param minTime The minimum valid time.
 * @param maxTime The maximum valid time.
 */
@Composable
internal fun TimeHintComponent(
    orientation: Orientation,
    minTime: Long? = null,
    maxTime: Long? = null,
) {

    if (minTime != null || maxTime != null) {

        val hintRes = if (minTime != null) R.string.scd_duration_dialog_at_least_placeholder
        else R.string.scd_duration_dialog_at_most_placeholder

        val time = minTime ?: maxTime
        ?: throw IllegalStateException("Hint is shown but min and max time values are null.")
        val values = getFormattedHintTime(time)

        TimeValueComponent(
            modifier = Modifier,
            orientation = orientation,
            values = values,
            hintValue = stringResource(id = hintRes)
        )
    }
}
