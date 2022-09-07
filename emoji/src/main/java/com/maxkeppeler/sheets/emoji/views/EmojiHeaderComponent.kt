package com.maxkeppeler.sheets.emoji.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.emoji.models.EmojiCategoryAppearance
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.vanniktech.emoji.EmojiCategory
import com.maxkeppeler.sheets.core.R as RC


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
                modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_normal_100)),
                contentPadding = PaddingValues(horizontal = dimensionResource(RC.dimen.scd_normal_100)),
                columns = GridCells.Fixed(categories.size),
                userScrollEnabled = false,
            ) {
                items(categoryIcons) { icon ->
                    val selected = categoryIcons.indexOf(icon) == selectedCategory
                    EmojiHeaderItemComponent(
                        imageVector = icon,
                        selected = selected,
                        onClick = { onChangeCategory(categoryIcons.indexOf(icon)) }
                    )
                }
            }
        }
        EmojiCategoryAppearance.TEXT -> {
            LazyRow(
                state = headerState,
                contentPadding = PaddingValues(horizontal = dimensionResource(RC.dimen.scd_normal_100)),
            ) {
                items(categories) {
                    val selected = categories.indexOf(it) == selectedCategory
                    EmojiTextHeaderItemComponent(
                        name = it.categoryNames["en"] ?: "",
                        selected = selected,
                        onClick = { onChangeCategory(categories.indexOf(it)) }
                    )
                }
            }
        }
    }
}