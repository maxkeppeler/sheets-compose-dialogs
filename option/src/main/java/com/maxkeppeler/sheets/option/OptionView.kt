@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.option

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
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import com.maxkeppeler.sheets.option.views.OptionBoundsComponent
import com.maxkeppeler.sheets.option.views.OptionComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Option view for the use-case to display a list or grid of options.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun OptionView(
    selection: OptionSelection,
    config: OptionConfig = OptionConfig(),
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
            is OptionSelection.Multiple -> Unit
            is OptionSelection.Single -> {
                if (selectedOptions.size > 1) {
                    throw IllegalStateException("$selection does not support multiple selected options.")
                }
            }
        }
        mutableStateListOf(*selectedOptions)
    }

    val onInvokeListener = {
        when (selection) {
            is OptionSelection.Multiple -> {
                val indices = selectedOptions.map { it.position }
                selection.onSelectOptions(indices, selectedOptions.toList())
            }
            is OptionSelection.Single -> {
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

    val selectOption: (Option) -> Unit = { option ->
        options[option.position] = option
    }

    val processSelection: (Option) -> Unit = { option ->
        when (selection) {
            is OptionSelection.Multiple -> {
                selection.maxChoices?.takeIf { selection.maxChoicesStrict }?.let { maxChoices ->
                    if (selectedOptions.size <= maxChoices) selectOption(option)
                } ?: selectOption(option)
            }
            is OptionSelection.Single -> {
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
            OptionBoundsComponent(
                selection = selection,
                selectedOptions = selectedOptions
            )

            OptionComponent(
                modifier = Modifier.dynamicContentWrapOrMaxHeight(this),
                config = config,
                options = options,
                onOptionChange = processSelection
            )
        },
        buttonsVisible = selection.withButtonView,
        buttons = {
            ButtonsComponent(
                onPositiveValid = {
                    when (selection) {
                        is OptionSelection.Single -> selectedOptions.isNotEmpty()
                        is OptionSelection.Multiple -> {
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





