@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.duration

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.duration.models.TimeConfig
import com.maxkeppeler.sheets.duration.models.TimeSelection

@ExperimentalMaterial3Api
@Composable
fun DurationDialog(
    show: MutableState<Boolean>,
    selection: TimeSelection,
    config: TimeConfig = TimeConfig(),
    header: Header? = null,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        DurationView(
            selection = selection,
            config = config,
            header = header,
            onCancel = { show.value = false }
        )
    }
}