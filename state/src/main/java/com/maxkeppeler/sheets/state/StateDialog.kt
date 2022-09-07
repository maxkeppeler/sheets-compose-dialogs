@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.state.models.StateSelection

/**
 * State dialog for the use-case to display various states.
 * @param show The state used to show and hide the dialog.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun StateDialog(
    show: MutableState<Boolean>,
    selection: StateSelection,
    config: StateConfig,
    header: Header? = null,
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
    ),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        StateView(
            selection = selection,
            config = config,
            header = header,
            onCancel = { show.value = false }
        )
    }
}