/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.maxkeppeler.sheets.option

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseState
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import java.io.Serializable

/**
 * Handles the option state.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class OptionState(
    val selection: OptionSelection,
    val config: OptionConfig,
    stateData: OptionStateData? = null
) : BaseState() {

    var options by mutableStateOf(stateData?.options ?: getInitOptions())
    var selectedOptions by mutableStateOf(getCurrentSelectedOptions())
    var valid by mutableStateOf(isValid())

    private fun getInitOptions(): List<Option> =
        selection.options.mapIndexed { i, option -> option.apply { position = i } }

    private fun getCurrentSelectedOptions(): List<Option> {
        val selectedOptions = options.toList().filter { it.selected }
        when (selection) {
            is OptionSelection.Multiple -> Unit
            is OptionSelection.Single -> {
                if (selectedOptions.size > 1) {
                    throw IllegalStateException("$selection does not support multiple selected options.")
                }
            }
        }
        return selectedOptions
    }

    private fun checkValid() {
        selectedOptions = getCurrentSelectedOptions()
        valid = isValid()
    }

    private fun updateOption(option: Option) {
        options = options.toMutableList().apply { set(option.position, option) }
        checkValid()
    }

    fun processSelection(option: Option) {
        when (selection) {
            is OptionSelection.Multiple -> {
                selection.maxChoices?.takeIf { selection.maxChoicesStrict }?.let { maxChoices ->
                    if (!option.selected || selectedOptions.size < maxChoices) updateOption(option)
                } ?: updateOption(option)
            }
            is OptionSelection.Single -> {
                selectedOptions.forEach { selectedOption ->
                    val disabledOption = selectedOption.copy(selected = false)
                        .apply { position = selectedOption.position }
                    updateOption(disabledOption)
                }
                updateOption(option)
            }
        }
    }

    private fun isValid(): Boolean = when (selection) {
        is OptionSelection.Single -> selectedOptions.isNotEmpty()
        is OptionSelection.Multiple -> {
            selectedOptions.isNotEmpty()
                    && (selection.minChoices?.let { selectedOptions.size >= it } ?: true)
                    && (selection.maxChoices?.let { selectedOptions.size <= it } ?: true)
        }
    }

    fun onFinish() {
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

    companion object {

        /**
         * [Saver] implementation.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: OptionSelection,
            config: OptionConfig
        ): Saver<OptionState, *> = Saver(
            save = { state -> OptionStateData(state.options) },
            restore = { data -> OptionState(selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class OptionStateData(
        val options: List<Option>
    ) : Serializable
}