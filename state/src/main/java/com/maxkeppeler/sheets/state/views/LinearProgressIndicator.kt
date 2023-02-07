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
package com.maxkeppeler.sheets.state.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun LinearProgressIndicator(
    indicator: ProgressIndicator.Linear,
) {

    val linearIndicatorModifier = Modifier
        .padding(dimensionResource(id = RC.dimen.scd_normal_150))

    indicator.customProgressIndicator?.invoke(indicator.value!!)
        ?: indicator.customIndicator?.invoke()
        ?: indicator.value?.let { progress ->
            if (indicator.showProgressPercentage) {
                Text(
                    modifier = Modifier.testTag(TestTags.STATE_LOADING_LABEL_PERCENTAGE),
                    text = "${progress.times(100)} %",
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            LinearProgressIndicator(
                progress = progress,
                modifier = linearIndicatorModifier
                    .testTags(TestTags.STATE_LOADING_LINEAR, progress)
            )
        } ?: run {
            LinearProgressIndicator(
                modifier = linearIndicatorModifier
                    .testTag(TestTags.STATE_LOADING_LINEAR)
            )
        }
}