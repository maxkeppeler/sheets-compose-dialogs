@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.duration.DurationDialog
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationFormat
import com.maxkeppeler.sheets.duration.models.DurationSelection
import java.time.LocalTime

@Composable
internal fun DurationSample2(closeSelection: () -> Unit) {

    val selectedTimeInSeconds = remember { mutableStateOf<Long>(240) }
    DurationDialog(
        show = true,
        selection = DurationSelection { newTimeInSeconds ->
            selectedTimeInSeconds.value = newTimeInSeconds
        },
        config = DurationConfig(
            timeFormat = DurationFormat.MM_SS,
            minTime = 30,
            maxTime = 240
        ),
        onClose = { closeSelection() }
    )
}