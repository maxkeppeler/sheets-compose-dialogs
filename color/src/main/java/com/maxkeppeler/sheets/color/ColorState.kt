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
package com.maxkeppeler.sheets.color

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.models.ColorSelection
import com.maxkeppeler.sheets.color.models.ColorSelectionMode
import java.io.Serializable

/**
 * Handles the color state.
 * @param context The context that is used to resolve the colors.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class ColorState(
    private val context: Context,
    val selection: ColorSelection,
    val config: ColorConfig = ColorConfig(),
    stateData: ColorStateData? = null,
) : BaseTypeState() {

    var color by mutableStateOf(stateData?.color ?: getInitColor())
    val colors by mutableStateOf(getInitColors())
    var displayMode by mutableStateOf(stateData?.displayMode ?: getInitDisplayMode())
    var valid by mutableStateOf(isValid())

    private fun getInitColor(): Int? =
        selection.selectedColor?.colorInInt(context)

    private fun getInitColors(): List<Int> =
        config.templateColors.getColorsAsInt(context)

    private fun getInitDisplayMode(): ColorSelectionMode =
        config.defaultDisplayMode.takeUnless {
            config.displayMode != null && config.displayMode != config.defaultDisplayMode
        } ?: config.displayMode ?: ColorSelectionMode.TEMPLATE

    private fun checkValid() {
        valid = isValid()
    }

    fun processSelection(newColor: Int) {
        color = newColor
        checkValid()
    }

    private fun isValid(): Boolean = color != null

    fun onFinish() {
        selection.onSelectColor(color!!)
    }

    override fun reset() {
        color = getInitColor()
    }

    companion object {

        /**
         * [Saver] implementation.
         * @param context The context that is used to resolve the colors.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            context: Context,
            selection: ColorSelection,
            config: ColorConfig
        ): Saver<ColorState, *> = Saver(
            save = { state -> ColorStateData(state.color, state.displayMode) },
            restore = { data -> ColorState(context, selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class ColorStateData(
        val color: Int?,
        val displayMode: ColorSelectionMode
    ) : Serializable
}

/**
 * Create a ColorState and remember it.
 * @param context The context that is used to resolve the colors.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun rememberColorState(
    context: Context,
    selection: ColorSelection,
    config: ColorConfig,
): ColorState = rememberSaveable(
    inputs = arrayOf(selection, config),
    saver = ColorState.Saver(context, selection, config),
    init = { ColorState(context, selection, config) }
)