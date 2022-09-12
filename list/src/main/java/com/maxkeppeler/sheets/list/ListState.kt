package com.maxkeppeler.sheets.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseState
import com.maxkeppeler.sheets.list.models.ListConfig
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import java.io.Serializable

/**
 * Handles the list state.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class ListState(
    val selection: ListSelection,
    val config: ListConfig,
    stateData: ListStateData? = null
) : BaseState() {

    var options by mutableStateOf(stateData?.options ?: getInitOptions())
    var selectedOptions by mutableStateOf(getCurrentSelectedOptions())
    var valid by mutableStateOf(isValid())

    private fun getInitOptions(): List<ListOption> =
        selection.options.mapIndexed { i, option -> option.apply { position = i } }

    private fun getCurrentSelectedOptions(): List<ListOption> {
        val selectedOptions = options.toList().filter { it.selected }
        when (selection) {
            is ListSelection.Multiple -> Unit
            is ListSelection.Single -> {
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

    private fun updateOption(option: ListOption) {
        options = options.toMutableList().apply { set(option.position, option) }
        checkValid()
    }

    fun processSelection(option: ListOption) {
        when (selection) {
            is ListSelection.Multiple -> {
                selection.maxChoices?.let { maxChoices ->
                    if (!option.selected || selectedOptions.size < maxChoices) updateOption(option)
                } ?: updateOption(option)
            }
            is ListSelection.Single -> {
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
        is ListSelection.Single -> selectedOptions.isNotEmpty()
        is ListSelection.Multiple -> {
            selectedOptions.isNotEmpty()
                    && (selection.minChoices?.let { selectedOptions.size >= it }
                ?: true)
                    && (selection.maxChoices?.let { selectedOptions.size <= it }
                ?: true)
        }
    }

    fun onFinish() {
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

    companion object {

        /**
         * [Saver] implementation.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: ListSelection,
            config: ListConfig
        ): Saver<ListState, *> = Saver(
            save = { state -> ListStateData(state.options) },
            restore = { data -> ListState(selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class ListStateData(
        val options: List<ListOption>
    ) : Serializable
}