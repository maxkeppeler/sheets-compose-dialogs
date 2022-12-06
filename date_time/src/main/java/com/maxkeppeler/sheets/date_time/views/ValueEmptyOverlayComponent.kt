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
package com.maxkeppeler.sheets.date_time.views

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig

/**
 * Component that is applied above the value component if no selection was made.
 * @param modifier The modifier that is applied to this component.
 */
@Composable
internal fun ValueEmptyOverlayComponent(
    config: DateTimeConfig,
    modifier: Modifier
) {
    Icon(
        modifier = modifier,
        imageVector = config.icons.ExpandMore,
        contentDescription = null
    )
}