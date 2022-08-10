@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.calendar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

@ExperimentalMaterial3Api
@Composable
fun CalendarDialog(
    show: MutableState<Boolean>,
    selection: CalendarSelection,
    config: CalendarConfig = CalendarConfig(),
    properties: DialogProperties = DialogProperties(),
) {
    if (!show.value) return

    Dialog(
        onDismissRequest = { show.value = false },
        properties = properties,
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface
        ) {
            CalendarView(
                config = config,
                selection = selection,
                onCancel = { show.value = false }
            )
        }
    }
}