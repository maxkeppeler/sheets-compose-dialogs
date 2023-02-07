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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.input.models.InputText

/**
 * Text component.
 * @param index The index of the input relative to all inputs.
 * @param input The input that this component reflects.
 */
@Composable
internal fun InputTextComponent(
    index: Int,
    input: InputText
) {

    Row(
        modifier = Modifier
            .testTags(TestTags.INPUT_ITEM_TEXT, index)
            .fillMaxWidth()
            .padding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.testTags(TestTags.INPUT_ITEM_TEXT_TEXT, index),
            text = input.text,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}