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

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeker.sheets.core.utils.TestTags

/**
 * Base component for a dialog.
 * @param state The state of the sheet.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 * @param onDialogClick Listener that is invoked when the dialog was clicked.
 * @param content The content to be displayed inside the dialog.
 */
@Composable
fun DialogBase(
    state: UseCaseState = rememberUseCaseState(true),
    properties: DialogProperties = DialogProperties(),
    onDialogClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    LaunchedEffect(Unit) {
        state.markAsEmbedded()
    }

    if (!state.visible) return

    val boxInteractionSource = remember { MutableInteractionSource() }
    val contentInteractionSource = remember { MutableInteractionSource() }

    Dialog(
        onDismissRequest = state::dismiss,
        properties = properties,
    ) {

        // Quick-fix for issue
        // https://stackoverflow.com/questions/71285843/how-to-make-dialog-re-measure-when-a-child-size-changes-dynamically/71287474#71287474

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .testTag(TestTags.DIALOG_BASE_CONTAINER)
                .fillMaxSize()
                .clickable(
                    interactionSource = boxInteractionSource,
                    indication = null,
                    onClick = { if (properties.dismissOnClickOutside) state.dismiss() }
                )
        ) {
            Surface(
                modifier = Modifier
                    .testTag(TestTags.DIALOG_BASE_CONTENT)
                    .fillMaxWidth()
                    .animateContentSize()
                    .clickable(
                        indication = null,
                        interactionSource = contentInteractionSource,
                        onClick = { onDialogClick?.invoke() }
                    ),
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surface,
                content = content
            )
        }
    }
}