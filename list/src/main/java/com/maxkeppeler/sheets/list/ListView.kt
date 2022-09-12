@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("UNUSED_PARAMETER")

package com.maxkeppeler.sheets.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.list.models.ListConfig
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.list.views.ListOptionBoundsComponent
import com.maxkeppeler.sheets.list.views.ListOptionComponent

/**
 * List view for the use-case to display a list of options.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun ListView(
    selection: ListSelection,
    config: ListConfig = ListConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    val coroutine = rememberCoroutineScope()
    val state = rememberSaveable(
        saver = ListState.Saver(selection, config),
        init = { ListState(selection, config) }
    )

    val processSelection: (ListOption) -> Unit = { option ->
        state.processSelection(option)
        BaseBehaviors.autoFinish(
            selection = selection,
            coroutine = coroutine,
            onSelection = state::onFinish,
            onFinished = onCancel,
            onDisableInput = state::disableInput
        )
    }

    FrameBase(
        header = { HeaderComponent(header) },
        contentPaddingValues = PaddingValues(0.dp),
        content = {
            ListOptionBoundsComponent(
                selection = selection,
                selectedOptions = state.selectedOptions
            )
            ListOptionComponent(
                modifier = Modifier.dynamicContentWrapOrMaxHeight(this),
                selection = selection,
                options = state.options,
                inputDisabled = state.inputDisabled,
                onOptionChange = processSelection
            )
        },
        buttonsVisible = selection.withButtonView,
        buttons = {
            ButtonsComponent(
                onPositiveValid = state.valid,
                selection = selection,
                onNegative = { selection.onNegativeClick?.invoke() },
                onPositive = state::onFinish,
                onCancel = onCancel
            )
        }
    )
}

