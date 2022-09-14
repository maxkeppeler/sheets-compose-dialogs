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


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maxkeppeler.sheets.duration.utils.getFormattedHintTime
import com.maxkeppeler.sheets.duration.R

/**
 * The info component that will show a hint if the selected time is out of the specified bounds.
 * @param minTimeValue The minimum valid time.
 * @param maxTimeValue The maximum valid time.
 */
@Composable
internal fun TimeInfoComponent(
    minTimeValue: Long? = null,
    maxTimeValue: Long? = null,
) {

    if (minTimeValue != null || maxTimeValue != null) {

        val labelRes =
            if (minTimeValue != null) R.string.sheets_at_least_placeholder
            else if (maxTimeValue != null) R.string.sheets_at_most_placeholder
            else null

        val options = getFormattedHintTime(minTimeValue ?: maxTimeValue!!)
        labelRes?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = stringResource(id = labelRes))
                TimeValueComponent(
                    valuePairs = options,
                    hintView = true
                )
            }
        }
    }
}
