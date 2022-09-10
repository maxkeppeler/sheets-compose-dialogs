@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.date_time

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection

/**
 * Date Time dialog for the use-case to select a date, time or both in a quick way.
 * @param show If the dialog should be displayed or not.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@Composable
fun DateTimeDialog(
    show: Boolean,
    selection: DateTimeSelection,
    config: DateTimeConfig = DateTimeConfig(),
    header: Header? = null,
    onClose: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        onClose = onClose,
        properties = properties,
    ) {
        DateTimeView(
            selection = selection,
            config = config,
            header = header,
            onCancel = onClose
        )
    }
}