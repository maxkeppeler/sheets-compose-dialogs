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
package com.maxkeppeler.sheets.emoji.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.emoji.models.EmojiCategoryAppearance
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.vanniktech.emoji.EmojiCategory


/**
 * The header component of the emojis. It shows the various categories of emojis.
 * @param config The general configuration for the dialog view.
 * @param categories The list of emoji categories.
 * @param categoryIcons The icons for the emoji categories.
 * @param selectedCategory The index of the category that is currently selected.
 * @param headerState The list state of the header that is used for textual categories.
 * @param onChangeCategory Listener that returns the new selected category index.
 */
@Composable
internal fun EmojiHeaderComponent(
    config: EmojiConfig,
    categories: Array<EmojiCategory>,
    categoryIcons: List<ImageVector>,
    selectedCategory: Int,
    headerState: LazyListState,
    onChangeCategory: (Int) -> Unit,
) {
    when (config.categoryAppearance) {
        EmojiCategoryAppearance.SYMBOL -> {
            LazyVerticalGrid(
                modifier = Modifier.testTags(TestTags.EMOJI_CATEGORY, config.categoryAppearance),
                columns = GridCells.Fixed(categories.size),
                userScrollEnabled = false,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.scd_small_25)),
            ) {
                itemsIndexed(categoryIcons) { index, icon ->
                    val selected = index == selectedCategory
                    EmojiHeaderItemComponent(
                        imageVector = icon,
                        index = index,
                        selected = selected,
                        onClick = { onChangeCategory(index) }
                    )
                }
            }
        }
        EmojiCategoryAppearance.TEXT -> {
            LazyRow(
                modifier = Modifier.testTags(TestTags.EMOJI_CATEGORY, config.categoryAppearance),
                state = headerState,
            ) {
                itemsIndexed(categories) { index, category ->
                    val selected = index == selectedCategory
                    EmojiTextHeaderItemComponent(
                        name = category.categoryNames["en"] ?: "",
                        selected = selected,
                        index = index,
                        onClick = { onChangeCategory(index) }
                    )
                }
            }
        }
    }
}