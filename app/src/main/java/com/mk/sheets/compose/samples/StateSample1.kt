/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.delay

@Composable
internal fun StateSample1(closeSelection: () -> Unit) {

    val sheetState = rememberUseCaseState(visible = false, onCloseRequest = { closeSelection() })

    val state = remember {
        val startState =
            State.Loading(labelText = "Fetching new data...", ProgressIndicator.Circular())
        mutableStateOf<State>(startState)
    }
    LaunchedEffect(Unit) {
        sheetState.show()
        delay(2000)
        state.value = State.Failure(labelText = "Fetching data failed. Trying again.")
        delay(2000)
        state.value =
            State.Loading(labelText = "Fetching new data...", ProgressIndicator.Circular())
        delay(2000)
        state.value = State.Success(labelText = "Data fetched..!")
        delay(2000)
        sheetState.hide()
    }

    StateDialog(
        state = sheetState,
        config = StateConfig(state = state.value)
    )
}