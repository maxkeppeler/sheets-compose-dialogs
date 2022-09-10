@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.emoji.EmojiDialog
import com.maxkeppeler.sheets.emoji.models.EmojiCategoryAppearance
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiProvider
import com.maxkeppeler.sheets.emoji.models.EmojiSelection

@Composable
internal fun EmojiSample2(closeSelection: () -> Unit) {

    EmojiDialog(
        show = true,
        selection = EmojiSelection.Unicode(
            withButtonView = false,
            onPositiveClick = { emojiUnicode ->
                // Handle selection
            }
        ),
        config = EmojiConfig(
            categoryAppearance = EmojiCategoryAppearance.TEXT,
            emojiProvider = EmojiProvider.IOS
        ),
        onClose = { closeSelection() }
    )
}