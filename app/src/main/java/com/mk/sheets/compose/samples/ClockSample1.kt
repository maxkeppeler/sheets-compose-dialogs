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
internal fun ClockSample1(closeSelection: () -> Unit) {

    val selectedTime = remember { mutableStateOf(LocalTime.of(23, 20, 0)) }
    ClockDialog(
        show = true,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            selectedTime.value = LocalTime.of(hours, minutes, 0)
        },
        config = ClockConfig(
            defaultTime = selectedTime.value,
            is24HourFormat = true
        ),
        onClose = { closeSelection() }
    )
}