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
package com.maxkeppeler.sheets.state.views

import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig

/**
 * The state component that displays the respective current state.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun StateComponent(config: StateConfig) {
    val state = config.state
    state.preView?.invoke()
    StateLabel(state)
    when (state) {
        is State.Loading -> StateLoadingComponent(state)
        is State.Failure -> StateFailureComponent(config, state)
        is State.Success -> StateSuccessComponent(config, state)
    }
    state.postView?.invoke()
}