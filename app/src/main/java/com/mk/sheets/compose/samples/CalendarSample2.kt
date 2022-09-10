@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@Composable
internal fun CalendarSample2(closeSelection: () -> Unit) {

    val selectedDate = remember { mutableStateOf<LocalDate?>(LocalDate.now().minusDays(3)) }

    CalendarDialog(
        show = true,
        config = CalendarConfig(
            yearSelection = true,
            style = CalendarStyle.WEEK,
        ),
        selection = CalendarSelection.Date(
            selectedDate = selectedDate.value
        ) { newDate ->
            selectedDate.value = newDate
        },
        onClose = { closeSelection() }
    )
}