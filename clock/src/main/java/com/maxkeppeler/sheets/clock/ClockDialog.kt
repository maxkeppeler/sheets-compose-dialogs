@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.clock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection

/**
 * Clock dialog for the use-case to to select a clock time.
 * @param show The state used to show and hide the dialog.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun ClockDialog(
    show: MutableState<Boolean>,
    selection: ClockSelection,
    config: ClockConfig = ClockConfig(),
    header: Header? = null,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        ClockView(
            selection = selection,
            config = config,
            header = header,
            onCancel = { show.value = false }
        )
    }
}