@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.emoji

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.BaseBehaviors
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
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
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun EmojiView(
    selection: EmojiSelection,
    config: EmojiConfig = EmojiConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    DisposableEffect(Unit) {
        EmojiInstaller.installProvider(config.emojiProvider)
        onDispose { EmojiInstaller.destroyProvider() }
    }

    val coroutine = rememberCoroutineScope()
    val state = rememberSaveable(
        saver = EmojiState.Saver(selection, config),
        init = { EmojiState(selection, config) }
    )

    val processSelection: (Emoji) -> Unit = { emoji ->
        state.processSelection(emoji)
        BaseBehaviors.autoFinish(
            selection = selection,
            coroutine = coroutine,
            onSelection = state::onFinish,
            onFinished = onCancel,
            onDisableInput = state::disableInput
        )
    }

    val headerState = rememberLazyListState()
    LaunchedEffect(state.selectedCategory) {
        headerState.animateScrollToItem(state.selectedCategory)
    }

    FrameBase(
        header = { HeaderComponent(header) },
        contentPaddingValues = PaddingValues(0.dp),
        content = {
            EmojiHeaderComponent(
                config = config,
                categories = state.categories,
                categoryIcons = Constants.CATEGORY_SYMBOLS,
                selectedCategory = state.selectedCategory,
                headerState = headerState,
                onChangeCategory = state::selectCategory
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .dynamicContentWrapOrMaxHeight(this)
                    .padding(top = dimensionResource(RC.dimen.scd_normal_100)),
                contentPadding = PaddingValues(
                    start = dimensionResource(RC.dimen.scd_normal_100),
                    end = dimensionResource(RC.dimen.scd_normal_100),
                    bottom = if (selection.withButtonView) 0.dp else dimensionResource(RC.dimen.scd_normal_100),
                ),
                columns = GridCells.Fixed(state.categories.size)
            ) {
                items(state.categoryEmojis, key = { it.unicode }) { emoji ->
                    EmojiItemComponent(
                        emoji = emoji,
                        selectedEmoji = state.selectedEmoji,
                        onClick = processSelection,
                    )
                }
            }
        },
        buttonsVisible = selection.withButtonView,
        buttons = {
            ButtonsComponent(
                onPositiveValid = state.valid,
                selection = selection,
                onNegative = { selection.onNegativeClick?.invoke() },
                onPositive = state::onFinish,
                onCancel = onCancel
            )
        }
    )
}

