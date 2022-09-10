@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class
)

package com.maxkeppeker.sheets.core

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase

/**
 * Core dialog that functions as the base of a custom use-case.
 * @param show If the dialog should be displayed or not.
 * @param selection The selection configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param body The body content to be displayed inside the dialog.
 * @param onPositiveValid Listener that is invoked to check if the dialog input is valid.
 * @param onClose Listener that is invoked to indicate that the use-case is done and the view should be closed.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun CoreDialog(
    show: Boolean,
    selection: CoreSelection,
    header: Header? = null,
    body: @Composable () -> Unit,
    onPositiveValid: () -> Boolean = { true },
    onClose: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        onClose = onClose,
        properties = properties,
    ) {
        CoreView(
            selection = selection,
            header = header,
            body = body,
            onCancel = onClose,
            onPositiveValid = onPositiveValid
        )
    }
}