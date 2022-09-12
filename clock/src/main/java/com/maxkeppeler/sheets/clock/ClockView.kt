@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.clock

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.maxkeppeler.sheets.clock.views.KeyboardComponent
import com.maxkeppeler.sheets.clock.views.TimeValueComponent

/**
 * Clock view for the use-case to to select a clock time.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun ClockView(
    selection: ClockSelection,
    config: ClockConfig = ClockConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    val context = LocalContext.current
    val state = rememberSaveable(
        saver = ClockState.Saver(context, selection, config),
        init = { ClockState(context, selection, config) }
    )

    FrameBase(
        header = { HeaderComponent(header) },
        contentPaddingValues = PaddingValues(top = 24.dp),
        content = {
            TimeValueComponent(
                unitValues = state.timeTextValues,
                isAm = state.isAm,
                is24hourFormat = state.is24HourFormat,
                valueIndex = state.valueIndex.value,
                groupIndex = state.groupIndex.value,
                onGroupClick = state::onValueGroupClick,
                onAm = state::onChange12HourFormatValue,
            )

            KeyboardComponent(
                keys = state.keys,
                disabledKeys = state.disabledKeys,
                onEnterValue = state::onEnterValue,
                onPrevAction = state::onPrevAction,
                onNextAction = state::onNextAction
            )
        },
        buttons = {
            ButtonsComponent(
                selection = selection,
                onNegative = { selection.onNegativeClick?.invoke() },
                onPositive = state::onFinish,
                onCancel = onCancel
            )
        }
    )
}

