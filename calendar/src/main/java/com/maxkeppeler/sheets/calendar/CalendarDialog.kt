@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

/**
 * Calendar dialog for the use-case to select a date or period in a typical calendar-view.
 * @param show The state used to show and hide the dialog.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun CalendarDialog(
    show: MutableState<Boolean>,
    selection: CalendarSelection,
    config: CalendarConfig = CalendarConfig(),
    header: Header? = null,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        CalendarView(
            config = config,
            header = header,
            selection = selection,
            onCancel = { show.value = false }
        )
    }
}