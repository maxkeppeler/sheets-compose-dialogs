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
@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarView
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import kotlinx.coroutines.launch

@Composable
fun BottomSheetSample(
    state: ModalBottomSheetState,
    screenContent: @Composable () -> Unit,
) {
    val coroutine = rememberCoroutineScope()
    val hideBottomSheet = { coroutine.launch { state.animateTo(ModalBottomSheetValue.Hidden) } }
    val dialogSheetState = rememberSheetState(visible = true, onCloseRequest = { hideBottomSheet() })

    ModalBottomSheetLayout(
        content = screenContent,
        sheetState = state,
        sheetContent = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
            ) {
                CalendarView(
                    sheetState = dialogSheetState,
                    config = CalendarConfig(
                        style = CalendarStyle.WEEK
                    ),
                    selection = CalendarSelection.Dates {},
                )
            }
        }
    )
}