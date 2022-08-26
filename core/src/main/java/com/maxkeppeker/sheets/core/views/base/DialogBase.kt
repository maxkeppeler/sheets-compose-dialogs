package com.maxkeppeker.sheets.core.views.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogBase(
    show: MutableState<Boolean>,
    properties: DialogProperties = DialogProperties(),
    onDialogClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    if (!show.value) return

    Dialog(
        onDismissRequest = { show.value = false },
        properties = properties,
    ) {

        // Quick-fix for issue: https://stackoverflow.com/questions/71285843/how-to-make-dialog-re-measure-when-a-child-size-changes-dynamically/71287474#71287474

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {
                    show.value = false
                }
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onDialogClick?.invoke()
                    },
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surface
            ) {
                content()
            }
        }
    }
}