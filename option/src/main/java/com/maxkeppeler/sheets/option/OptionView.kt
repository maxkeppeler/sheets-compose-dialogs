@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.option

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import com.maxkeppeler.sheets.option.views.OptionBoundsComponent
import com.maxkeppeler.sheets.option.views.OptionComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun OptionView(
    selection: OptionSelection,
    config: OptionConfig = OptionConfig(),
    onCancel: () -> Unit = {},
    header: Header? = null,
    ) {

    val coroutine = rememberCoroutineScope()
    val options = remember { mutableStateListOf(*selection.options.toTypedArray()) }
    val selectedOptions = remember(options.toList()) {
        val selectedOptions = options.toList().filter { it.selected }.toTypedArray()
        when (selection) {
            is OptionSelection.Multiple -> Unit
            is OptionSelection.Single -> {
                if (selectedOptions.size > 1)
                    throw IllegalStateException("OptionSelection type Single can not have multiple selected options.")
            }
        }
        mutableStateListOf(*selectedOptions)
    }

    val onInvokeListener = {
        when (selection) {
            is OptionSelection.Multiple -> {
                val indices = selectedOptions.toList().map { options.indexOf(it) }
                selection.onSelectOptions(indices, selectedOptions.toList())
            }
            is OptionSelection.Single -> {
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

    val selectOption: (Int, Option) -> Unit = { index, option -> options[index] = option }
    val processSelection: (Int, Option) -> Unit = { index, option ->
        when (selection) {
            is OptionSelection.Multiple -> {
                selection.maxChoices?.takeIf { selection.maxChoicesStrict }?.let { maxChoices ->
                    if (selectedOptions.size < maxChoices) selectOption(index, option)
                } ?: selectOption(index, option)
            }
            is OptionSelection.Single -> {
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

        OptionBoundsComponent(
            selection = selection,
            selectedOptions = selectedOptions
        )

        OptionComponent(
            modifier = Modifier
                .weight(1f, false)
                .heightIn(max = 350.dp),
            selection = selection,
            config = config,
            options = options,
            onOptionChange = processSelection
        )

        if (selection.withButtonView) {
            ButtonComponent(
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





