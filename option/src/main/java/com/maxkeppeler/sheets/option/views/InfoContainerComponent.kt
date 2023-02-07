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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.option.views


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionDetails
import com.maxkeppeler.sheets.core.R as RC

/**
 * The info container that is displayed on an option.
 * @param config The general configuration.
 * @param modifier The modifier that is applied to this component.
 * @param optionInfo The OptionDetails data that this item represents.
 * @param onClick The listener that is invoked when the info button is clicked.
 */
@Composable
internal fun InfoContainerComponent(
    config: OptionConfig,
    modifier: Modifier = Modifier,
    optionInfo: OptionDetails?,
    onClick: () -> Unit
) {

    if (optionInfo == null) return

    Box(modifier = modifier) {

        FilledIconButton(
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.background),
            modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(50))
                .size(dimensionResource(RC.dimen.scd_size_150)),
            onClick = { onClick() }
        ) {

            Icon(
                modifier = Modifier
                    .size(dimensionResource(RC.dimen.scd_size_100)),
                imageVector = config.icons.Info,
                contentDescription = null,
            )
        }

    }
}