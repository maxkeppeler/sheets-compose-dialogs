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
package com.maxkeppeker.sheets.core.views.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags
import kotlinx.coroutines.launch

/**
 * Base component for a popup.
 * @param state The state of the sheet.
 * @param onPopupClick Listener that is invoked when the popup was clicked.
 * @param alignment Alignment of the popup relative to the anchor.
 * @param offset Offset of the popup relative to the anchor.
 * @param properties PopupProperties for further customization of this popup's behavior.
 * @param content The content to be displayed inside the dialog.
 */
@ExperimentalMaterial3Api
@Composable
fun BottomSheetBase(
    state: UseCaseState = rememberUseCaseState(true),
    allowDismiss: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    val sheetState: SheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        state.markAsEmbedded()
        state.setFinishListener {
            coroutineScope.launch { sheetState.hide() }
        }
        state.setShowListener {
            coroutineScope.launch { sheetState.show() }
        }
    }

    ModalBottomSheet(
        modifier = Modifier
            .testTag(TestTags.SHEET_BASE_CONTAINER),
        onDismissRequest = { if (allowDismiss) state.dismiss() },
        sheetState = sheetState,
        content = {
            Column(
                Modifier.testTag(TestTags.SHEET_BASE_CONTENT),
                content = content
            )
        }
    )
}