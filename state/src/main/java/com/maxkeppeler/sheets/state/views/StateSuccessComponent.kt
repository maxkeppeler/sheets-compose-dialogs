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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.core.R as RC

/**
 * The success state component.
 * @param config The general configuration for the dialog view.
 * @param state The success state data.
 */
@Composable
internal fun StateSuccessComponent(
    config: StateConfig,
    state: State.Success
) {
    state.customView?.invoke() ?: run {
        Icon(
            modifier = Modifier
                .testTag(TestTags.STATE_SUCCESS)
                .padding(vertical = dimensionResource(id = RC.dimen.scd_normal_100))
                .size(dimensionResource(id = RC.dimen.scd_size_350)),
            imageVector = config.icons.Check,
            contentDescription = null,
            tint = Color.Green
        )
    }
}