@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.info

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.info.models.InfoSelection

/**
 * Info dialog for the use-case to display simple information.
 * @param show The state used to show and hide the dialog.
 * @param selection The selection configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param body The body content to be displayed inside the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun InfoDialog(
    show: MutableState<Boolean>,
    selection: InfoSelection,
    header: Header? = null,
    body: Body,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        InfoView(
            selection = selection,
            header = header,
            body = body,
            onCancel = { show.value = false }
        )
    }
}