@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import android.util.Range
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
internal fun CalendarSample3(closeSelection: () -> Unit) {

    val selectedDateRange = remember {
        val value = Range(LocalDate.now().minusDays(5), LocalDate.now())
        mutableStateOf(value)
    }

    CalendarDialog(
        show = true,
        config = CalendarConfig(
            style = CalendarStyle.MONTH,
        ),
        selection = CalendarSelection.Period(
            selectedRange = selectedDateRange.value
        ) { startDate, endDate ->
            selectedDateRange.value = Range(startDate, endDate)
        },
        onClose = { closeSelection() }
    )
}