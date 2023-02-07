/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeler.sheets.duration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationSelection
import com.maxkeppeler.sheets.duration.utils.*
import java.io.Serializable

/**
 * Handles the duration state.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class DurationState(
    val selection: DurationSelection,
    val config: DurationConfig,
    stateData: DurationStateData? = null
) : BaseTypeState() {

    var timeTextValue by mutableStateOf(stateData?.timeTextValue ?: getInitTimeTextValue())
    var timeInfoInSeconds by mutableStateOf(getInitTimeInfoInSeconds())
    var values by mutableStateOf(getInitValuePairs())
    var indexOfFirstValue by mutableStateOf(getInitIndexOfFirstValue())
    val keys by mutableStateOf(getInputKeys(config))
    var valid by mutableStateOf(isValid())

    private fun getInitTimeTextValue(): StringBuilder {
        return parseCurrentTime(config.timeFormat, config.currentTime)
    }

    private fun getInitTimeInfoInSeconds(): Triple<Long, Long?, Long?> {
        val value = parseToSeconds(timeTextValue, config.timeFormat)
        val minTime = if (value != 0L && value < config.minTime) config.minTime else null
        val maxTime = if (value != 0L && value > config.maxTime) config.maxTime else null
        return Triple(value, minTime, maxTime)
    }

    private fun getInitValuePairs(): List<Pair<String, Label>> {
        return getValuePairs(timeTextValue, config)
    }

    private fun getInitIndexOfFirstValue(): Int? {
        return values
            .indexOfFirst { runCatching { it.first.toInt() != 0 }.getOrNull() ?: false }
            .takeUnless { it == -1 }
    }

    private fun checkValid() {
        valid = isValid()
    }

    private fun refreshTime() {
        timeInfoInSeconds = getInitTimeInfoInSeconds()
        values = getInitValuePairs()
        indexOfFirstValue = getInitIndexOfFirstValue()
        checkValid()
    }

    private fun isValid(): Boolean = timeInfoInSeconds.first > 0
            && timeInfoInSeconds.second == null
            && timeInfoInSeconds.third == null

    fun onEnterValue(value: String) {
        val newTimeBuilder = timeTextValue.apply {
            if (length >= config.timeFormat.length) {
                repeat(value.length) { deleteCharAt(0) }
            }
            append(value)
        }
        timeTextValue = StringBuilder(newTimeBuilder.toString())
        refreshTime()
    }

    fun onBackspaceAction() {
        val newTimeBuilder = timeTextValue.apply {
            deleteCharAt(lastIndex)
            insert(0, 0.toString())
        }
        timeTextValue = StringBuilder(newTimeBuilder)
        refreshTime()
    }

    fun onEmptyAction() {
        timeTextValue = StringBuilder(parseCurrentTime(config.timeFormat))
        refreshTime()
    }

    fun onFinish() {
        selection.onPositiveClick(timeInfoInSeconds.first)
    }

    override fun reset() {
        timeTextValue = getInitTimeTextValue()
        timeInfoInSeconds = getInitTimeInfoInSeconds()
        values = getInitValuePairs()
        indexOfFirstValue = getInitIndexOfFirstValue()
    }

    companion object {

        /**
         * [Saver] implementation.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: DurationSelection,
            config: DurationConfig
        ): Saver<DurationState, *> = Saver(
            save = { state -> DurationStateData(state.timeTextValue) },
            restore = { data -> DurationState(selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class DurationStateData(
        val timeTextValue: StringBuilder,
    ) : Serializable
}

/**
 * Create a DurationState and remember it.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun rememberDurationState(
    selection: DurationSelection,
    config: DurationConfig,
): DurationState = rememberSaveable(
    inputs = arrayOf(selection, config),
    saver = DurationState.Saver(selection, config),
    init = { DurationState(selection, config) }
)