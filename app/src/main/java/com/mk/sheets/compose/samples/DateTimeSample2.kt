@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import java.time.LocalDate

@Composable
internal fun DateTimeSample2(closeSelection: () -> Unit) {

    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }
    DateTimeDialog(
        show = true,
        selection = DateTimeSelection.Date { newDate ->
            selectedDate.value = newDate
        },
        onClose = { closeSelection() }
    )
}