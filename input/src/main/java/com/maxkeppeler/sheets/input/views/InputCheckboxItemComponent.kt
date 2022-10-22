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
package com.maxkeppeler.sheets.input.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
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
 * The CheckBox item for the CheckBox-Group.
 * @param index The index of the checkbox item.
 * @param text The text of the CheckBox.
 * @param selected If the CheckBox is selected.
 * @param onSelected The listener that is invoked when the CheckBox was clicked.
 */
@Composable
internal fun InputCheckboxItemComponent(
    index: Int,
    text: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .testTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM, index)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            modifier = Modifier
                .testTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_CHECKBOX, index)
                .padding(start = dimensionResource(RC.dimen.scd_small_50))
                .size(dimensionResource(RC.dimen.scd_normal_150)),
            checked = selected,
            onCheckedChange = { onSelected() },
        )

        Column(
            modifier = Modifier
                .padding(vertical = dimensionResource(RC.dimen.scd_small_100))
                .padding(start = dimensionResource(RC.dimen.scd_normal_100))
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {

            Text(
                modifier = Modifier.testTags(TestTags.INPUT_ITEM_CHECKBOX_GROUP_ITEM_TEXT, index),
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}