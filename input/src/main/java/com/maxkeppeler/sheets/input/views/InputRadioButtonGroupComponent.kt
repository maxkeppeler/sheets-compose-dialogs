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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.input.models.Input
import com.maxkeppeler.sheets.input.models.InputRadioButtonGroup
import com.maxkeppeler.sheets.core.R as RC

/**
 * RadioButton-Group component.
 * @param input The input that this component reflects.
 * @param onInputUpdated The listener that returns the updated input.
 */
@Composable
internal fun InputRadioButtonGroupComponent(
    input: InputRadioButtonGroup,
    onInputUpdated: (Input) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(input.value) }

    LaunchedEffect(selectedIndex) {
        onInputUpdated(input.apply {
            value = selectedIndex
        })
    }

    Column {
        input.items.forEachIndexed { index, item ->
            InputRadioButtonItemComponent(
                text = item,
                selected = selectedIndex == index,
                onSelected = { selectedIndex = index }
            )
            if (index != input.items.lastIndex) {
                Spacer(Modifier.height(dimensionResource(RC.dimen.scd_small_50)))
            }
        }
    }
}