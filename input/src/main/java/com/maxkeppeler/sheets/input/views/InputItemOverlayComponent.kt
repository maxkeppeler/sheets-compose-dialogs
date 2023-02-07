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
package com.maxkeppeler.sheets.input.views

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.input.models.InputConfig
import com.maxkeppeler.sheets.core.R as RC

/**
 * The overlay for an input item type component when the data is required.
 * @param index The index of the input relative to all inputs.
 */
@Composable
internal fun BoxScope.InputItemOverlayComponent(
    config: InputConfig,
    index: Int
) {
    Icon(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .testTags(TestTags.INPUT_ITEM_OVERLAY, index)
            .padding(dimensionResource(RC.dimen.scd_small_100))
            .size(dimensionResource(RC.dimen.scd_size_50)),
        imageVector = config.icons.Star,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.error
    )
}