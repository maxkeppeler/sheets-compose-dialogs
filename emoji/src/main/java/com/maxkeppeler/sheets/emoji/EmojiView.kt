@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.emoji

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Popup
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.emoji.models.EmojiCategoryStyle
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiSelection
import com.maxkeppeler.sheets.emoji.utils.EmojiInstaller
import com.vanniktech.emoji.Emoji
import com.vanniktech.emoji.EmojiCategory
import com.vanniktech.emoji.EmojiTextView
import com.vanniktech.emoji.google.GoogleEmojiProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun EmojiView(
    selection: EmojiSelection,
    config: EmojiConfig = EmojiConfig(),
    onCancel: () -> Unit = {},
    header: Header? = null,
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

    val selectedCategory = remember { mutableStateOf(0) }

    val emojis = remember(selectedCategory.value) {
        val value = categories.value[selectedCategory.value].emojis
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

    LaunchedEffect(selectedCategory.value) {
        headerState.animateScrollToItem(selectedCategory.value)
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

    Column(modifier = Modifier.wrapContentHeight()) {

        HeaderComponent(header)

        EmojiHeaderComponent(
            config = config,
            categories = categories,
            categoryIcons = categoryIcons,
            selectedCategory = selectedCategory,
            headerState = headerState
        )

        LazyVerticalGrid(
            modifier = Modifier
                .heightIn(max = 400.dp)
                .padding(top = 16.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = if (selection.withButtonView) 0.dp else 16.dp,
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

        if (selection.withButtonView) {
            ButtonComponent(
                onPositiveValid = { selectedEmoji.value != null },
                negativeButtonText = selection.negativeButtonText,
                positiveButtonText = selection.positiveButtonText,
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
    }
}

@Composable
private fun EmojiHeaderComponent(
    config: EmojiConfig,
    categories: MutableState<Array<EmojiCategory>>,
    categoryIcons: MutableState<List<ImageVector>>,
    selectedCategory: MutableState<Int>,
    headerState: LazyListState
) {
    when (config.categoryStyle) {
        EmojiCategoryStyle.SYMBOL -> {
            LazyVerticalGrid(
                modifier = Modifier.padding(top = 16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                columns = GridCells.Fixed(categories.value.size),
                userScrollEnabled = false,
            ) {
                items(categoryIcons.value) { icon ->
                    val selected = categoryIcons.value.indexOf(icon) == selectedCategory.value
                    EmojiHeaderItemComponent(
                        imageVector = icon,
                        selected = selected,
                        onClick = {
                            selectedCategory.value = categoryIcons.value.indexOf(icon)
                        }
                    )
                }
            }
        }
        EmojiCategoryStyle.TEXT -> {
            LazyRow(
                state = headerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(categories.value) {
                    val selected = categories.value.indexOf(it) == selectedCategory.value
                    EmojiTextHeaderItemComponent(
                        name = it.categoryNames["en"] ?: "",
                        selected = selected,
                        onClick = {
                            selectedCategory.value = categories.value.indexOf(it)
                        },
                    )
                }
            }
        }
    }
}

@Composable
internal fun EmojiTextHeaderItemComponent(
    name: String,
    onClick: () -> Unit,
    selected: Boolean
) {
    Text(
        modifier = Modifier
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(50))
            .background(if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 4.dp),
        text = name,
        style = if (selected) MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary)
        else MaterialTheme.typography.labelLarge
    )
}

@Composable
internal fun EmojiHeaderItemComponent(
    imageVector: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(2.dp)
            .aspectRatio(1f, true)
            .clip(if (selected) RoundedCornerShape(50) else MaterialTheme.shapes.small)
            .background(if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .clickable { onClick() }
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = imageVector,
            contentDescription = "",
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
internal fun EmojiItemComponent(
    emoji: Emoji,
    selectedEmoji: Emoji?,
    onClick: (Emoji) -> Unit
) {
    val size = remember { mutableStateOf(IntSize.Zero) }
    val showVariants = remember { mutableStateOf(false) }

    VariantsPopup(
        show = showVariants,
        baseEmoji = emoji,
        selectedEmoji = selectedEmoji,
        size = size,
        onClick = onClick,
    )

    BoxWithConstraints(
        modifier = Modifier
            .onGloballyPositioned {
                if (size.value != it.size) size.value = it.size
            }
            .padding(2.dp)
            .clip(MaterialTheme.shapes.small)
            .background(
                when {
                    selectedEmoji == emoji -> MaterialTheme.colorScheme.primaryContainer
                    emoji.variants.any { it == selectedEmoji } -> MaterialTheme.colorScheme.tertiaryContainer
                    else -> Color.Transparent
                }
            )
            .combinedClickable(
                onClick = { onClick(emoji) },
                onLongClick = {
                    if (emoji.variants.isNotEmpty()) showVariants.value = true
                }
            )
            .padding(2.dp),
    ) {

        AndroidView(
            modifier = Modifier.align(Alignment.Center),
            factory = { context ->
                EmojiTextView(context).apply {
                    setEmojiSize(constraints.maxWidth, false)
                    text = emoji.unicode
                }
            },
        )
    }
}

@Composable
private fun VariantsPopup(
    show: MutableState<Boolean>,
    size: MutableState<IntSize>,
    baseEmoji: Emoji,
    selectedEmoji: Emoji?,
    onClick: (Emoji) -> Unit
) {
    if (show.value) {
        val itemSize = LocalDensity.current.run { size.value.width.toDp() }
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { show.value = false },
        ) {
            Box(Modifier.wrapContentWidth()) {
                Surface(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .widthIn(max = itemSize * 6),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 4.dp,
                ) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(16.dp),
                        columns = GridCells.Adaptive(itemSize),
                        userScrollEnabled = false,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        items(baseEmoji.variants, key = { it.unicode }) { emoji ->
                            EmojiItemComponent(
                                emoji = emoji,
                                selectedEmoji = selectedEmoji,
                                onClick = onClick
                            )
                        }
                    }
                }
            }
        }
    }
}

