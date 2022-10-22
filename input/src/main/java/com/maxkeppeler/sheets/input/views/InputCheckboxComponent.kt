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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.input.models.Input
import com.maxkeppeler.sheets.input.models.InputCheckbox
import com.maxkeppeler.sheets.core.R as RC

/**
 * Checkbox component.
 * @param index The index of the input relative to all inputs.
 * @param input The input that this component reflects.
 * @param onInputUpdated The listener that returns the updated input.
 */
@Composable
internal fun InputCheckboxComponent(
    index: Int,
    input: InputCheckbox,
    onInputUpdated: (Input) -> Unit
) {
    var checked by remember { mutableStateOf(input.value) }

    LaunchedEffect(checked) {
        onInputUpdated(input.apply {
            value = checked
        })
    }

    Row(
        modifier = Modifier
            .testTags(TestTags.INPUT_ITEM_CHECKBOX, index)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { checked = !checked },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            modifier = Modifier
                .testTags(TestTags.INPUT_ITEM_CHECKBOX_CHECKBOX, index)
                .padding(start = dimensionResource(RC.dimen.scd_small_50))
                .size(dimensionResource(RC.dimen.scd_normal_150)),
            checked = checked,
            onCheckedChange = { checked = it }
        )

        Column(
            modifier = Modifier
                .padding(vertical = dimensionResource(RC.dimen.scd_small_150))
                .padding(start = dimensionResource(RC.dimen.scd_normal_100))
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Text(
                modifier = Modifier.testTags(TestTags.INPUT_ITEM_CHECKBOX_TEXT, index),
                text = input.text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}