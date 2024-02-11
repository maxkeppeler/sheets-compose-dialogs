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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.emoji

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.StateHandler
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiSelection
import com.maxkeppeler.sheets.emoji.utils.Constants
import com.maxkeppeler.sheets.emoji.utils.EmojiInstaller
import com.maxkeppeler.sheets.emoji.views.EmojiHeaderComponent
import com.maxkeppeler.sheets.emoji.views.EmojiItemComponent
import com.vanniktech.emoji.Emoji
import com.maxkeppeler.sheets.core.R as RC

/**
 * Emoji view for the use-case to to select any emoji or a variant, if available.
 * @param useCaseState The state of the sheet.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 */
@ExperimentalMaterial3Api
@Composable
fun EmojiView(
    useCaseState: UseCaseState,
    selection: EmojiSelection,
    config: EmojiConfig = EmojiConfig(),
    header: Header? = null,
) {
    DisposableEffect(Unit) {
        EmojiInstaller.installProvider(config.emojiProvider)
        onDispose { EmojiInstaller.destroyProvider() }
    }

    val coroutine = rememberCoroutineScope()
    val emojiState = rememberEmojiState(selection, config)
    StateHandler(useCaseState, emojiState)

    val processSelection: (Emoji) -> Unit = { emoji ->
        emojiState.processSelection(emoji)
        BaseBehaviors.autoFinish(
            selection = selection,
            coroutine = coroutine,
            onSelection = emojiState::onFinish,
            onFinished = useCaseState::finish,
            onDisableInput = emojiState::disableInput
        )
    }

    val headerState = rememberLazyListState()
    LaunchedEffect(emojiState.selectedCategory) {
        headerState.animateScrollToItem(emojiState.selectedCategory)
    }

    FrameBase(
        header = header,
        config = config,
        layout = {
            EmojiHeaderComponent(
                config = config,
                categories = emojiState.categories,
                categoryIcons = Constants.getCategorySymbols(config),
                selectedCategory = emojiState.selectedCategory,
                headerState = headerState,
                onChangeCategory = emojiState::selectCategory
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .dynamicContentWrapOrMaxHeight(this)
                    .padding(top = dimensionResource(RC.dimen.scd_normal_100)),
                contentPadding = PaddingValues(
                    bottom = if (selection.withButtonView) 0.dp else dimensionResource(RC.dimen.scd_normal_100),
                ),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_25)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(RC.dimen.scd_small_25)),
                columns = GridCells.Fixed(emojiState.categories.size)
            ) {
                items(emojiState.categoryEmojis, key = { it.unicode }) { emoji ->
                    EmojiItemComponent(
                        emoji = emoji,
                        selectedEmoji = emojiState.selectedEmoji,
                        onClick = processSelection,
                    )
                }
            }
        },
        buttonsVisible = selection.withButtonView
    ) { orientation ->
        ButtonsComponent(
            orientation = orientation,
            onPositiveValid = emojiState.valid,
            selection = selection,
            onNegative = { selection.onNegativeClick?.invoke() },
            onPositive = emojiState::onFinish,
            state = useCaseState,
        )
    }
}

