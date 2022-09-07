@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.duration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationSelection
import com.maxkeppeler.sheets.duration.utils.getInputOptions
import com.maxkeppeler.sheets.duration.utils.getValuePairs
import com.maxkeppeler.sheets.duration.utils.parseCurrentTime
import com.maxkeppeler.sheets.duration.utils.parseToSeconds
import com.maxkeppeler.sheets.duration.views.TimeDisplayComponent
import com.maxkeppeler.sheets.duration.views.KeyboardComponent

/**
 * Duration view for the use-case to to select a duration time.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun DurationView(
    selection: DurationSelection,
    config: DurationConfig = DurationConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
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

    FrameBase(
        header = { HeaderComponent(header) },
        contentPaddingValues = PaddingValues(top = 24.dp),
        content = {
            TimeDisplayComponent(
                indexOfFirstValue = indexOfFirstValue,
                valuePairs = valuePairs.value,
                minTimeValue = timeInfoInSeconds.second,
                maxTimeValue = timeInfoInSeconds.third
            )
            KeyboardComponent(
                options = options,
                onEnterValue = onEnterValue,
                onBackspaceAction = onBackspaceAction,
                onEmptyAction = onEmptyAction
            )
        },
        buttons = {
            ButtonsComponent(
                onPositiveValid = {
                    timeInfoInSeconds.first > 0
                            && timeInfoInSeconds.second == null
                            && timeInfoInSeconds.third == null
                },
                negativeButton = selection.negativeButton,
                positiveButton = selection.positiveButton,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    selection.onPositiveClick(timeInfoInSeconds.first)
                    onCancel()
                }
            )
        }
    )
}
