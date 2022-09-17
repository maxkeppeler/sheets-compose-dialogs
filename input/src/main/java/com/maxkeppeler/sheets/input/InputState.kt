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
package com.maxkeppeler.sheets.input

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseState
import com.maxkeppeler.sheets.input.models.Input
import com.maxkeppeler.sheets.input.models.InputSelection
import java.io.Serializable

/**
 * Handles the list state.
 * @param selection The selection configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class InputState(
    val selection: InputSelection,
    stateData: InputStateData? = null
) : BaseState() {

    var input = mutableStateListOf(*(stateData?.input ?: getInitInput()).toTypedArray())
    var valid by mutableStateOf(isValid())

    private fun getInitInput(): List<Input> =
        selection.input.mapIndexed { i, input ->
            input.apply {
                position = i
                valid = input.isValid()
            }
        }

    private fun checkValid() {
        valid = isValid()
    }

    private fun isValid(): Boolean =
        input.toMutableList().all { it.isValid() }

    fun updateInput(changedInput: Input) {
        input[changedInput.position] = changedInput
        val listOld = input.toMutableList()
        input.clear()
        input.addAll(listOld)
        checkValid()
    }

    fun onFinish() {
        val bundle = Bundle()
        input.forEach { input ->
            if (input.isInput()) {
                input.onResult()
                input.putValue(bundle)
            }
        }
        selection.onPositiveClick?.invoke(bundle)
    }

    companion object {

        /**
         * [Saver] implementation.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: InputSelection,
        ): Saver<InputState, *> = Saver(
            save = { state -> InputStateData(state.input) },
            restore = { data -> InputState(selection, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class InputStateData(
        val input: List<Input>
    ) : Serializable
}