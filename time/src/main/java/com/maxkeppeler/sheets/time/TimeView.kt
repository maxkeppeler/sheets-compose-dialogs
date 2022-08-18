@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.time

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.time.models.TimeConfig
import com.maxkeppeler.sheets.time.models.TimeSelection
import com.maxkeppeler.sheets.time.utils.getInputOptions
import com.maxkeppeler.sheets.time.utils.getValuePairs
import com.maxkeppeler.sheets.time.utils.parseCurrentTime
import com.maxkeppeler.sheets.time.utils.parseToSeconds
import com.maxkeppeler.sheets.time.views.TimeDisplayComponent
import com.maxkeppeler.sheets.time.views.TimeInputComponent

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun TimeView(
    selection: TimeSelection,
    config: TimeConfig = TimeConfig(),
    onCancel: () -> Unit = {},
    header: Header? = null,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 24.dp),
    ) {

        HeaderComponent(header)

        var timeTextValue by rememberSaveable {
            val parseTime = parseCurrentTime(config.timeFormat, config.currentTime)
            mutableStateOf(parseTime)
        }

        val timeInfoInSeconds by remember(timeTextValue) {
            val value = parseToSeconds(timeTextValue, config.timeFormat)
            val minTime = if (value != 0L && value < config.minTime) config.minTime else null
            val maxTime = if (value != 0L && value > config.maxTime) config.maxTime else null
            mutableStateOf(Triple(value, minTime, maxTime))
        }

        val onEnterValue: (String) -> Unit = {
            val newTimeBuilder = timeTextValue.apply {
                if (length >= config.timeFormat.length) {
                    repeat(it.length) { deleteCharAt(0) }
                }
                append(it)
            }
            timeTextValue = StringBuilder(newTimeBuilder.toString())
        }

        val onBackspaceAction: () -> Unit = {
            val newTimeBuilder = timeTextValue.apply {
                deleteCharAt(lastIndex)
                insert(0, 0.toString())
            }
            timeTextValue = StringBuilder(newTimeBuilder)
        }

        val onEmptyAction: () -> Unit = {
            timeTextValue = StringBuilder(parseCurrentTime(config.timeFormat))
        }

        val valuePairs = remember(timeTextValue) {
            val value = getValuePairs(timeTextValue, config)
            mutableStateOf(value)
        }

        val options by rememberSaveable { mutableStateOf(getInputOptions(config)) }

        val indexOfFirstValue by remember(valuePairs.value) {
            val indexOfFirstValue =
                valuePairs.value
                    .indexOfFirst { runCatching { it.first.toInt() != 0 }.getOrNull() ?: false }
                    .takeUnless { it == -1 }
            mutableStateOf(indexOfFirstValue)
        }

        TimeDisplayComponent(
            indexOfFirstValue = indexOfFirstValue,
            valuePairs = valuePairs,
            minTimeValue = timeInfoInSeconds.second,
            maxTimeValue = timeInfoInSeconds.third
        )

        TimeInputComponent(
            options = options,
            onEnterValue = onEnterValue,
            onBackspaceAction = onBackspaceAction,
            onEmptyAction = onEmptyAction
        )

        ButtonComponent(
            onPositiveValid = {
                timeInfoInSeconds.first > 0
                        && timeInfoInSeconds.second == null
                        && timeInfoInSeconds.third == null
            },
            negativeButtonText = selection.negativeButtonText,
            positiveButtonText = selection.positiveButtonText,
            onNegative = { selection.onNegativeClick?.invoke(); onCancel() },
            onPositive = { selection.onPositiveClick(); onCancel() }
        )
    }
}
