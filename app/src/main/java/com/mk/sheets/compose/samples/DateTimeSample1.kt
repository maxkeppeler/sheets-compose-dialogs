@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import java.time.LocalDateTime

@Composable
internal fun DateTimeSample1(closeSelection: () -> Unit) {

    val selectedDateTime = remember { mutableStateOf<LocalDateTime?>(null) }
    DateTimeDialog(
        show = true,
        selection = DateTimeSelection.DateTime { newDateTime ->
            selectedDateTime.value = newDateTime
        },
        onClose = { closeSelection() }
    )
}