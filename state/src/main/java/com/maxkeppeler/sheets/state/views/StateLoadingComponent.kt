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
package com.maxkeppeler.sheets.state.views

import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State

@Composable
internal fun StateLoadingComponent(state: State.Loading) {
    when (val indicator = state.indicator) {
        is ProgressIndicator.Circular -> CircularProgressIndicator(indicator)
        is ProgressIndicator.Linear -> LinearProgressIndicator(indicator)
        else -> state.customView?.invoke()
    }
}