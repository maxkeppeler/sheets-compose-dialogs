@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.duration

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationSelection

/**
 * Duration view for the use-case to to select a duration time.
 * @param show The state used to show and hide the dialog.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun DurationDialog(
    show: MutableState<Boolean>,
    selection: DurationSelection,
    config: DurationConfig = DurationConfig(),
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