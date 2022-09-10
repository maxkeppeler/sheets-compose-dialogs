@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import java.time.LocalTime

@Composable
internal fun DateTimeSample3(closeSelection: () -> Unit) {

    val selectedTime = remember { mutableStateOf<LocalTime?>(null) }
    DateTimeDialog(
        show = true,
        selection = DateTimeSelection.Time { newTime ->
            selectedTime.value = newTime
        },
        onClose = { closeSelection() }
    )
}