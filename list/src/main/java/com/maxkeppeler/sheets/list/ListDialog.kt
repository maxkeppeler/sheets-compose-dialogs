@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.list.models.ListConfig
import com.maxkeppeler.sheets.list.models.ListSelection

/**
 * List dialog for the use-case to display a list of options.
 * @param show The state used to show and hide the dialog.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun ListDialog(
    show: MutableState<Boolean>,
    selection: ListSelection,
    config: ListConfig = ListConfig(),
    header: Header? = null,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        InfoView(
            selection = selection,
            config= config,
            header = header,
            onCancel = { show.value = false }
        )
    }
}