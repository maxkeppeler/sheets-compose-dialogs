@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.list.models.ListConfig
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.list.views.ListOptionBoundsComponent
import com.maxkeppeler.sheets.list.views.ListOptionComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun InfoView(
    selection: ListSelection,
    config: ListConfig = ListConfig(),
    onCancel: () -> Unit = {},
    header: Header? = null,
) {
    val coroutine = rememberCoroutineScope()
    val options = remember { mutableStateListOf(*selection.options.toTypedArray()) }
    val selectedOptions = remember(options.toList()) {
        val selectedOptions = options.toList().filter { it.selected }.toTypedArray()
        when (selection) {
            is ListSelection.Multiple -> Unit
            is ListSelection.Single -> {
                if (selectedOptions.size > 1)
                    throw IllegalStateException("OptionSelection type Single can not have multiple selected options.")
            }
        }
        mutableStateListOf(*selectedOptions)
    }

    val onInvokeListener = {
        when (selection) {
            is ListSelection.Multiple -> {
                val indices = selectedOptions.toList().map { options.indexOf(it) }
                selection.onSelectOptions(indices, selectedOptions.toList())
            }
            is ListSelection.Single -> {
                val option = selectedOptions.first()
                val index = options.indexOf(option)
                selection.onSelectOption(index, option)
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

    val selectOption: (Int, ListOption) -> Unit = { index, option -> options[index] = option }
    val processSelection: (Int, ListOption) -> Unit = { index, option ->
        when (selection) {
            is ListSelection.Multiple -> {
                selection.maxChoices?.let { maxChoices ->
                    if (selectedOptions.size <= maxChoices) selectOption(index, option)
                } ?: selectOption(index, option)
            }
            is ListSelection.Single -> {
                selectedOptions.forEach { selectedOption ->
                    val selectedOptionIndex = options.indexOf(selectedOption)
                    options[selectedOptionIndex] = selectedOption.copy(selected = false)
                }
                selectOption(index, option)
            }
        }
        if (!selection.withButtonView) onSelection(onInvokeListener)
    }

    Column(modifier = Modifier.wrapContentHeight()) {

        HeaderComponent(header)

        ListOptionBoundsComponent(
            selection = selection,
            selectedOptions = selectedOptions
        )

        ListOptionComponent(
            modifier = Modifier
                .weight(1f, false)
                .heightIn(max = 350.dp),
            selection = selection,
            options = options,
            onOptionChange = processSelection
        )

        if (selection.withButtonView) {
            ButtonComponent(
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
        }
    }
}

