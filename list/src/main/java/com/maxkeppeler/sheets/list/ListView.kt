@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("UNUSED_PARAMETER")

package com.maxkeppeler.sheets.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.utils.BaseModifiers
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.list.models.ListConfig
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.list.views.ListOptionBoundsComponent
import com.maxkeppeler.sheets.list.views.ListOptionComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
fun InfoView(
    selection: ListSelection,
    config: ListConfig = ListConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    val coroutine = rememberCoroutineScope()
    val options = remember {
        val sortedOptions =
            selection.options.mapIndexed { i, option -> option.apply { position = i } }
        val value = sortedOptions.toTypedArray()
        mutableStateListOf(*value)
    }

    val selectedOptions = remember(options.toList()) {
        val selectedOptions = options.toList().filter { it.selected }.toTypedArray()
        when (selection) {
            is ListSelection.Multiple -> Unit
            is ListSelection.Single -> {
                if (selectedOptions.size > 1) {
                    throw IllegalStateException("$selection does not support multiple selected options.")
                }
            }
        }
        mutableStateListOf(*selectedOptions)
    }

    val onInvokeListener = {
        when (selection) {
            is ListSelection.Multiple -> {
                val indices = selectedOptions.map { it.position }
                selection.onSelectOptions(indices, selectedOptions.toList())
            }
            is ListSelection.Single -> {
                val option = selectedOptions.first()
                selection.onSelectOption(option.position, option)
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

    val selectOption: (ListOption) -> Unit = { option ->
        options[option.position] = option
    }

    val processSelection: (ListOption) -> Unit = { option ->
        when (selection) {
            is ListSelection.Multiple -> {
                selection.maxChoices?.let { maxChoices ->
                    if (selectedOptions.size <= maxChoices) selectOption(option)
                } ?: selectOption(option)
            }
            is ListSelection.Single -> {
                selectedOptions.forEach { selectedOption ->
                    val disabledOption = selectedOption.copy(selected = false)
                        .apply { position = selectedOption.position }
                    options[selectedOption.position] = disabledOption
                }
                selectOption(option)
            }
        }
        if (!selection.withButtonView) onSelection(onInvokeListener)
    }

    FrameBase(
        header = { HeaderComponent(header) },
        contentPaddingValues = PaddingValues(0.dp),
        content = {
            ListOptionBoundsComponent(
                selection = selection,
                selectedOptions = selectedOptions
            )
            ListOptionComponent(
                modifier = Modifier.dynamicContentWrapOrMaxHeight(this),
                selection = selection,
                options = options,
                onOptionChange = processSelection
            )
        },
        buttonsVisible = selection.withButtonView,
        buttons = {
            ButtonsComponent(
                onPositiveValid = {
                    when (selection) {
                        is ListSelection.Single -> selectedOptions.isNotEmpty()
                        is ListSelection.Multiple -> {
                            selectedOptions.isNotEmpty()
                                    && (selection.minChoices?.let { selectedOptions.size >= it }
                                ?: true)
                                    && (selection.maxChoices?.let { selectedOptions.size <= it }
                                ?: true)
                        }
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

