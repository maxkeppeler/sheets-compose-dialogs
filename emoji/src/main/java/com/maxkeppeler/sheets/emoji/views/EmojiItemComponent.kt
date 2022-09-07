@file:OptIn(ExperimentalFoundationApi::class)

package com.maxkeppeler.sheets.emoji.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.viewinterop.AndroidView
import com.vanniktech.emoji.Emoji
import com.vanniktech.emoji.EmojiTextView
import com.maxkeppeler.sheets.core.R as RC


/**
 * The emoji item component.
 * @param emoji The name of the category.
 * @param selectedEmoji The emoji that is currently selected.
 * @param onClick The listener that returns the selected emoji.
 */
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
            .padding(dimensionResource(RC.dimen.scd_small_25))
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
            .padding(dimensionResource(RC.dimen.scd_small_25)),
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