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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.core.R as RC

/**
 * The RadioButton item for the RadioButton-Group.
 * @param index The index of the input relative to all inputs.
 * @param text The text of the RadioButton.
 * @param selected If the RadioButton is selected.
 * @param onSelected The listener that is invoked when the RadioButton was clicked.
 */
@Composable
internal fun InputRadioButtonItemComponent(
    index: Int,
    text: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .testTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM, index)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable(!selected) { onSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            modifier = Modifier
                .testTags(TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON, index)
                .padding(start = dimensionResource(RC.dimen.scd_small_50))
                .size(dimensionResource(RC.dimen.scd_normal_150)),
            selected = selected,
            onClick = { onSelected() },
        )

        Column(
            modifier = Modifier
                .padding(vertical = dimensionResource(RC.dimen.scd_small_100))
                .padding(start = dimensionResource(RC.dimen.scd_normal_100))
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {

            Text(
                modifier = Modifier.testTags(
                    TestTags.INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_TEXT,
                    index
                ),
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}