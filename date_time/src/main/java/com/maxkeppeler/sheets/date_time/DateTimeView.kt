@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.date_time

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.date_time.views.PickerComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

/**
 * Date Time dialog for the use-case to select a date, time or both in a quick way.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun DateTimeView(
    selection: DateTimeSelection,
    config: DateTimeConfig = DateTimeConfig(),
    onCancel: () -> Unit = {},
    header: Header? = null,
) {
    val coroutine = rememberCoroutineScope()

    var dateSelection by remember { mutableStateOf<LocalDate?>(null) }
    var timeSelection by remember { mutableStateOf<LocalTime?>(null) }

    val onInvokeListener = {
        when (selection) {
            is DateTimeSelection.Date -> selection.onPositiveClick(dateSelection!!)
            is DateTimeSelection.Time -> selection.onPositiveClick(timeSelection!!)
            is DateTimeSelection.DateTime -> {
                val value = dateSelection!!.atTime(timeSelection!!)
                selection.onPositiveClick(value)
            }
        }
    }

    val onSelection: (() -> Unit) -> Unit = { selectionUnit ->
        coroutine.launch {
            delay(BaseConstants.SUCCESS_DISMISS_DELAY)
            selectionUnit()
            onCancel()
        }
    }

    val processSelection: () -> Unit = {
        if (!selection.withButtonView) {
            when {
                selection is DateTimeSelection.Date && dateSelection != null -> {
                    onSelection(onInvokeListener)
                }
                selection is DateTimeSelection.Time && timeSelection != null -> {
                    onSelection(onInvokeListener)
                }
                selection is DateTimeSelection.DateTime
                        && dateSelection != null && timeSelection != null -> {
                    onSelection(onInvokeListener)
                }
            }
        }
    }

    FrameBase(
        header = { HeaderComponent(header) },
        content = {
            val datePicker = @Composable {
                PickerComponent(
                    config = config,
                    locale = selection.locale,
                    formatStyle = selection.dateFormatStyle!!,
                    onDateValueChange = { dateSelection = it; processSelection() }
                )
            }

            val timePicker = @Composable {
                PickerComponent(
                    config = config,
                    locale = selection.locale,
                    formatStyle = selection.timeFormatStyle!!,
                    onTimeValueChange = { timeSelection = it; processSelection() }
                )
            }

            when (selection) {
                is DateTimeSelection.Date -> datePicker()
                is DateTimeSelection.Time -> timePicker()
                is DateTimeSelection.DateTime -> {
                    if (selection.startWithTime) {
                        if (timeSelection == null) timePicker()
                        if (timeSelection != null) datePicker()
                    } else {
                        if (dateSelection == null) datePicker()
                        if (dateSelection != null) timePicker()
                    }
                }
            }
        },
        buttonsVisible = selection.withButtonView,
        buttons = {
            ButtonsComponent(
                onPositiveValid = {
                    when (selection) {
                        is DateTimeSelection.Date -> dateSelection != null
                        is DateTimeSelection.Time -> timeSelection != null
                        is DateTimeSelection.DateTime -> dateSelection != null && timeSelection != null
                    }
                },
                negativeButton = selection.negativeButton,
                positiveButton = selection.positiveButton,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    onInvokeListener()
                    onCancel()
                }
            )
        }
    )
}

