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
package com.maxkeppeker.sheets.core.models.base

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import java.io.Serializable

/**
 * Handles the base behavior of any use-case view and the dialog, if dialog is used.
 * @param visible If the dialog is initially visible.
 * @param embedded If the view is embedded (in a Dialog, PopUp, BottomSheet or another container that has its own state).
 * @param onCloseRequest The listener that is invoked when the dialog was closed through any cause.
 * @param onFinishedRequest The listener that is invoked when the dialog's use-case was finished by the user accordingly (negative, positive, selection).
 * @param onDismissRequest The listener that is invoked when the dialog was dismissed.
 */
class SheetState(
    visible: Boolean,
    embedded: Boolean,
    val onFinishedRequest: (SheetState.() -> Unit)? = null,
    val onDismissRequest: (SheetState.() -> Unit)? = null,
    val onCloseRequest: (SheetState.() -> Unit)? = null,
) {
    var visible by mutableStateOf(visible)
    var embedded by mutableStateOf(embedded)
    var reset by mutableStateOf(false)

    fun show() {
        visible = true
    }

    fun hide() {
        visible = false
        reset = true
        onDismissRequest?.invoke(this)
        onCloseRequest?.invoke(this)
    }

    private fun invokeReset() {
        reset = true
    }

    fun clearReset() {
        reset = false
    }

    // Closed
    internal fun dismiss() {
        invokeReset()
        if (!embedded) visible = false
        onDismissRequest?.invoke(this)
        onCloseRequest?.invoke(this)
    }

    // Use case finished
    fun finish() {
        Log.d("SheetState", "finish")
        /*
            We don't want to remove the view itself,
            but inform through the state that the use-case is done.
            The parent container (Dialog, PopUp, BottomSheet)
            can be hidden with the use-case view.
         */
        invokeReset()
        if (!embedded) visible = false

        onFinishedRequest?.invoke(this)
        onCloseRequest?.invoke(this)
    }

    internal fun markAsEmbedded() {
        Log.d("SheetState", "markAsEmbedded")
        embedded = false
    }

    companion object {

        fun Saver(): Saver<SheetState, *> = Saver(
            save = { state ->
                SheetStateData(
                    visible = state.visible,
                    embedded = state.embedded,
                    onCloseRequest = state.onCloseRequest,
                    onFinishedRequest = state.onFinishedRequest,
                    onDismissRequest = state.onDismissRequest
                )
            },
            restore = { data ->
                SheetState(
                    visible = data.visible,
                    embedded = data.embedded,
                    onCloseRequest = data.onCloseRequest,
                    onFinishedRequest = data.onFinishedRequest,
                    onDismissRequest = data.onDismissRequest,
                )
            }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class SheetStateData(
        val visible: Boolean,
        val embedded: Boolean,
        val onCloseRequest: (SheetState.() -> Unit)?,
        val onFinishedRequest: (SheetState.() -> Unit)?,
        val onDismissRequest: (SheetState.() -> Unit)?,
    ) : Serializable
}

/**
 * Create a SheetState and remember it.
 * @param visible The initial visibility.
 * @param embedded if the use-case is embedded in a container (dialog, bottomSheet, popup, ...)
 * @param onCloseRequest The listener that is invoked when the dialog was closed through any cause.
 * @param onFinishedRequest The listener that is invoked when the dialog's use-case was finished by the user accordingly (negative, positive, selection).
 * @param onDismissRequest The listener that is invoked when the dialog was dismissed.
 */
@Composable
fun rememberSheetState(
    visible: Boolean = true,
    embedded: Boolean = true,
    onCloseRequest: (SheetState.() -> Unit)? = null,
    onFinishedRequest: (SheetState.() -> Unit)? = null,
    onDismissRequest: (SheetState.() -> Unit)? = null,
): SheetState =
    rememberSaveable(
        saver = SheetState.Saver(),
        init = {
            SheetState(
                visible = visible,
                embedded = embedded,
                onCloseRequest = onCloseRequest,
                onFinishedRequest = onFinishedRequest,
                onDismissRequest = onDismissRequest
            )
        }
    )