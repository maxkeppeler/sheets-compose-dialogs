@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.state.models.StateSelection

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