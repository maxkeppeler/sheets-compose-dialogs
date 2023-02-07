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

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeker.sheets.core.views.IconComponent
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.core.R as RC

/**
 * The header for an input item type component.
 * @param index The index of the input relative to all inputs.
 * @param header The header that will be displayed.
 */
@Composable
internal fun InputItemHeaderComponent(
    index: Int,
    header: InputHeader
) {
    Column(
        modifier = Modifier
            .testTags(TestTags.INPUT_ITEM_HEADER, index)
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(RC.dimen.scd_small_100)
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            header.icon?.let {
                IconComponent(
                    modifier = Modifier
                        .testTags(TestTags.INPUT_ITEM_HEADER_ICON, index)
                        .size(dimensionResource(RC.dimen.scd_size_100)),
                    iconSource = it,
                )
            }

            header.title?.let { title ->
                val startPadding =
                    if (header.icon != null) dimensionResource(RC.dimen.scd_small_100) else 0.dp
                Text(
                    modifier = Modifier
                        .testTags(TestTags.INPUT_ITEM_HEADER_TITLE, index)
                        .padding(start = startPadding),
                    text = title,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }

        header.body?.let { text ->
            Text(
                modifier = Modifier
                    .testTags(TestTags.INPUT_ITEM_HEADER_BODY, index)
                    .padding(top = dimensionResource(RC.dimen.scd_small_50)),
                text = text,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}