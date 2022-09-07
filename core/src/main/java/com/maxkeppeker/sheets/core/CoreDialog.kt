@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeker.sheets.core

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.views.base.DialogBase

/**
 * Core dialog that functions as the base of a custom use-case.
 * @param show The state used to show and hide the dialog.
 * @param selection The selection configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param body The body content to be displayed inside the dialog.
 * @param onPositiveValid Listener that is invoked to check if the dialog input is valid.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun CoreDialog(
    show: MutableState<Boolean>,
    selection: CoreSelection,
    header: Header? = null,
    body: @Composable () -> Unit,
    onPositiveValid: () -> Boolean = { true },
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        CoreView(
            selection = selection,
            header = header,
            body = body,
            onCancel = { show.value = false },
            onPositiveValid = onPositiveValid
        )
    }
}