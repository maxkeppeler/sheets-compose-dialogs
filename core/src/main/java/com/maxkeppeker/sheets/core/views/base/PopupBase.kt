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
package com.maxkeppeker.sheets.core.views.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags

/**
 * Base component for a popup.
 * @param state The state of the sheet.
 * @param onPopupClick Listener that is invoked when the popup was clicked.
 * @param alignment Alignment of the popup relative to the anchor.
 * @param offset Offset of the popup relative to the anchor.
 * @param properties PopupProperties for further customization of this popup's behavior.
 * @param content The content to be displayed inside the dialog.
 */
@Composable
fun PopupBase(
    state: UseCaseState = rememberUseCaseState(true),
    alignment: Alignment = Alignment.TopStart,
    offset: IntOffset = IntOffset(0, 0),
    properties: PopupProperties = PopupProperties(),
    content: @Composable () -> Unit,
) {
    LaunchedEffect(Unit) { state.markAsEmbedded() }

    if (!state.visible) return

    Popup(
        alignment = alignment,
        onDismissRequest = state::dismiss,
        offset = offset,
        properties = properties
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .widthIn(max = 300.dp)
                .testTag(TestTags.POPUP_BASE_CONTAINER)
        ) {
            Surface(
                modifier = Modifier
                    .testTag(TestTags.POPUP_BASE_CONTENT),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 4.dp,
                content = content,
            )
        }
    }
}