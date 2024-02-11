/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeler.sheets.emoji

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.views.BaseTypeState
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiSelection
import com.vanniktech.emoji.Emoji
import com.vanniktech.emoji.EmojiCategory
import com.vanniktech.emoji.google.GoogleEmojiProvider
import java.io.Serializable

/**
 * Handles the emoji state.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param stateData The data of the state when the state is required to be restored.
 */
internal class EmojiState(
    val selection: EmojiSelection,
    val config: EmojiConfig,
    stateData: EmojiStateData? = null
) : BaseTypeState() {

    var selectedEmoji by mutableStateOf<Emoji?>(stateData?.selectedEmoji)
    var selectedCategory by mutableStateOf(0)
    val categories by mutableStateOf(getInitCategories())
    var categoryEmojis by mutableStateOf(getInitEmojis())
    var valid by mutableStateOf(isValid())

    private fun getInitCategories(): Array<EmojiCategory> =
        GoogleEmojiProvider().categories

    private fun getInitEmojis(): List<Emoji> {
        return categories[selectedCategory].emojis
    }

    private fun checkValid() {
        valid = isValid()
    }

    fun selectCategory(categoryIndex: Int) {
        selectedCategory = categoryIndex
        categoryEmojis = getInitEmojis()
    }

    fun processSelection(newEmoji: Emoji) {
        selectedEmoji = newEmoji
        checkValid()
    }

    private fun isValid(): Boolean = selectedEmoji != null

    fun onFinish() {
        when (selection) {
            is EmojiSelection.Emoji -> {
                selection.onPositiveClick(selectedEmoji!!)
            }
            is EmojiSelection.Unicode -> {
                selection.onPositiveClick(selectedEmoji!!.unicode)
            }
        }
    }

    override fun reset() {
        selectedEmoji = null
        selectedCategory = 0
    }

    companion object {

        /**
         * [Saver] implementation.
         * @param context The context that is used to resolve the colors.
         * @param selection The selection configuration for the dialog view.
         * @param config The general configuration for the dialog view.
         */
        fun Saver(
            selection: EmojiSelection,
            config: EmojiConfig
        ): Saver<EmojiState, *> = Saver(
            save = { state -> EmojiStateData(state.selectedEmoji) },
            restore = { data -> EmojiState(selection, config, data) }
        )
    }

    /**
     * Data class that stores the important information of the current state
     * and can be used by the [Saver] to save and restore the state.
     */
    data class EmojiStateData(
        val selectedEmoji: Emoji?
    ) : Serializable
}

/**
 * Create a EmojiState and remember it.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 */
@Composable
internal fun rememberEmojiState(
    selection: EmojiSelection,
    config: EmojiConfig
): EmojiState = rememberSaveable(
    inputs = arrayOf(selection, config),
    saver = EmojiState.Saver(selection, config),
    init = { EmojiState(selection, config) }
)