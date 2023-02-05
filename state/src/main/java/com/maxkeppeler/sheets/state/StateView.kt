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
@file:Suppress("UNUSED_PARAMETER")

package com.maxkeppeler.sheets.state

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.state.models.StateSelection
import com.maxkeppeler.sheets.state.views.StateComponent
import com.maxkeppeler.sheets.core.R as RC

/**
 * State dialog for the use-case to display various states.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun StateView(
    selection: StateSelection? = null,
    config: StateConfig,
    header: Header? = null,
) {
    FrameBase(
        header = header,
        config = config,
        contentHorizontalAlignment = Alignment.CenterHorizontally,
        horizontalContentPadding = PaddingValues(
            horizontal = dimensionResource(id = RC.dimen.scd_normal_100),
            vertical = dimensionResource(id = RC.dimen.scd_normal_100)
        ),
        content = {
            StateComponent(config = config)
        },
    )
}





