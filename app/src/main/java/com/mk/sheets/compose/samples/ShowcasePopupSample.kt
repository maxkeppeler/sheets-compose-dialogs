@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarView
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle

@Composable
internal fun ShowcasePopupSample(visible: Boolean, onHide: () -> Unit) {

    if (!visible) return

    Popup(
        alignment = Alignment.Center,
        onDismissRequest = { onHide() },
    ) {
        Box(
            Modifier
                .wrapContentWidth()
                .widthIn(max = 300.dp)
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 4.dp,
            ) {
                CalendarView(
                    sheetState = rememberSheetState(
                        onCloseRequest = { onHide() },
                    ),
                    config = CalendarConfig(
                        style = CalendarStyle.WEEK
                    ),
                    selection = CalendarSelection.Dates {},
                )
            }
        }
    }
}