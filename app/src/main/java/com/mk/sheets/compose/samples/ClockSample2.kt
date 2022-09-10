@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalTime

@Composable
internal fun ClockSample2(closeSelection: () -> Unit) {

    val selectedTime = remember { mutableStateOf<LocalTime?>(null) }
    ClockDialog(
        show = true,
        selection = ClockSelection.HoursMinutesSeconds { hours, minutes, seconds ->
            selectedTime.value = LocalTime.of(hours, minutes, seconds)
        },
        config = ClockConfig(
            is24HourFormat = false
        ),
        onClose = { closeSelection() }
    )
}