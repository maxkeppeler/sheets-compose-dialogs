@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.emoji

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiSelection
import com.maxkeppeler.sheets.emoji.utils.EmojiInstaller
import com.maxkeppeler.sheets.emoji.views.EmojiHeaderComponent
import com.maxkeppeler.sheets.emoji.views.EmojiItemComponent
import com.vanniktech.emoji.Emoji
import com.vanniktech.emoji.google.GoogleEmojiProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    val selectedEmoji = rememberSaveable { mutableStateOf<Emoji?>(null) }

    val categories = remember {
        val categories = GoogleEmojiProvider().categories
        mutableStateOf(categories)
    }

    var selectedCategory by remember { mutableStateOf(0) }

    val emojis = remember(selectedCategory) {
        val value = categories.value[selectedCategory].emojis
        mutableStateOf(value)
    }
    val categoryIcons = remember {
        val value = listOf(
            Icons.Rounded.EmojiEmotions,
            Icons.Rounded.EmojiNature,
            Icons.Rounded.EmojiFoodBeverage,
            Icons.Rounded.EmojiTransportation,
            Icons.Rounded.EmojiEvents,
            Icons.Rounded.EmojiObjects,
            Icons.Rounded.EmojiSymbols,
            Icons.Rounded.EmojiFlags,
        )
        mutableStateOf(value)
    }
    val headerState = rememberLazyListState()

    LaunchedEffect(selectedCategory) {
        headerState.animateScrollToItem(selectedCategory)
    }

    val onInvokeListener = {
        when (selection) {
            is EmojiSelection.Emoji -> {
                selection.onPositiveClick(selectedEmoji.value!!)
            }
            is EmojiSelection.Unicode -> {
                selection.onPositiveClick(selectedEmoji.value!!.unicode)
            }
        }
    }

    val onSelection: (() -> Unit) -> Unit = { selectionUnit ->
        coroutine.launch {
            delay(BaseConstants.SUCCESS_DISMISS_DELAY)
            selectionUnit()
            onCancel()
        }
    }

    val onEmojiClickHandler: (Emoji) -> Unit = { emoji ->
        selectedEmoji.value = emoji
        if (!selection.withButtonView) onSelection(onInvokeListener)
    }

    FrameBase(
        header = { HeaderComponent(header) },
        contentPaddingValues = PaddingValues(0.dp),
        content = {
            EmojiHeaderComponent(
                config = config,
                categories = categories.value,
                categoryIcons = categoryIcons.value,
                selectedCategory = selectedCategory,
                headerState = headerState,
                onChangeCategory = { selectedCategory = it }
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
                columns = GridCells.Fixed(categories.value.size)
            ) {
                items(emojis.value, key = { it.unicode }) { emoji ->
                    EmojiItemComponent(
                        emoji = emoji,
                        selectedEmoji = selectedEmoji.value,
                        onClick = onEmojiClickHandler,
                    )
                }
            }
        },
        buttonsVisible = selection.withButtonView,
        buttons = {
            ButtonsComponent(
                onPositiveValid = { selectedEmoji.value != null },
                negativeButton = selection.negativeButton,
                positiveButton = selection.positiveButton,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    onInvokeListener()
                    onCancel()
                }
            )
        }
    )
}

