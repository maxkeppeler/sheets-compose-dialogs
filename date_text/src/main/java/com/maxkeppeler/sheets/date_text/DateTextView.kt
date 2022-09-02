@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalSnapperApi::class)

package com.maxkeppeler.sheets.date_text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.date_text.models.DateTextConfig
import com.maxkeppeler.sheets.date_text.models.DateTextSelection
import com.maxkeppeler.sheets.date_text.views.PickerComponent
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun DateTextView(
    selection: DateTextSelection,
    config: DateTextConfig = DateTextConfig(),
    onCancel: () -> Unit = {},
    header: Header? = null,
) {
    val coroutine = rememberCoroutineScope()

    var dateSelection by remember { mutableStateOf<LocalDate?>(null) }
    var timeSelection by remember { mutableStateOf<LocalTime?>(null) }

    val onInvokeListener = {
        when (selection) {
            is DateTextSelection.Date -> selection.onPositiveClick(dateSelection!!)
            is DateTextSelection.Time -> selection.onPositiveClick(timeSelection!!)
            is DateTextSelection.DateTime -> selection.onPositiveClick(
                dateSelection!!.atTime(
                    timeSelection!!
                )
            )
        }
    }

    val onSelection: (() -> Unit) -> Unit = { selectionUnit ->
        coroutine.launch {
            delay(BaseConstants.SUCCESS_DISMISS_DELAY)
            selectionUnit()
            onCancel()
        }
    }

    val processSelection: ()-> Unit = {
        if (!selection.withButtonView) {
            when {
                selection is DateTextSelection.Date && dateSelection != null -> {
                    onSelection(onInvokeListener)
                }
                selection is DateTextSelection.Time && timeSelection != null -> {
                    onSelection(onInvokeListener)
                }
                selection is DateTextSelection.DateTime
                        && dateSelection != null && timeSelection != null -> {
                    onSelection(onInvokeListener)
                }
            }
        }
    }

    Column(modifier = Modifier.wrapContentHeight()) {

        HeaderComponent(
            header = header,
            noHeaderView = {
                Spacer(modifier = Modifier.height(12.dp))
            }
        )

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
            is DateTextSelection.Date -> datePicker()
            is DateTextSelection.Time -> timePicker()
            is DateTextSelection.DateTime -> {
                if (selection.startWithTime) {
                    if (timeSelection == null) timePicker()
                    if (timeSelection != null) datePicker()
                } else {
                    if (dateSelection == null) datePicker()
                    if (dateSelection != null) timePicker()
                }
            }
        }

        if (selection.withButtonView) {
            ButtonComponent(
                onPositiveValid = {
                    when (selection) {
                        is DateTextSelection.Date -> dateSelection != null
                        is DateTextSelection.Time -> timeSelection != null
                        is DateTextSelection.DateTime -> dateSelection != null && timeSelection != null
                    }
                },
                negativeButtonText = selection.negativeButtonText,
                positiveButtonText = selection.positiveButtonText,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    onInvokeListener()
                    onCancel()
                }
            )
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

