@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.clock_time

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.clock_time.models.ClockTimeConfig
import com.maxkeppeler.sheets.clock_time.models.ClockTimeSelection

@ExperimentalMaterial3Api
@Composable
fun ClockTimeDialog(
    show: MutableState<Boolean>,
    selection: ClockTimeSelection,
    config: ClockTimeConfig = ClockTimeConfig(),
    header: Header? = null,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        ClockTimeView(
            selection = selection,
            config = config,
            header = header,
            onCancel = { show.value = false }
        )
    }
}