@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

/**
 * Calendar dialog for the use-case to select a date or period in a typical calendar-view.
 * @param show If the dialog should be displayed or not.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param onClose Listener that is invoked to indicate that the use-case is done and the view should be closed.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun CalendarDialog(
    show: Boolean,
    selection: CalendarSelection,
    config: CalendarConfig = CalendarConfig(),
    header: Header? = null,
    onClose: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        onClose = onClose,
        properties = properties,
    ) {
        CalendarView(
            config = config,
            header = header,
            selection = selection,
            onCancel = onClose
        )
    }
}