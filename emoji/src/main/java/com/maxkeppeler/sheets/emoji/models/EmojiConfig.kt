package com.maxkeppeler.sheets.emoji.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * The general configuration for the emoji dialog.
 * @param categoryAppearance The appearance of the categories.
 * @param emojiProvider The emoji provider that will be used to render the emojis.
 */
data class EmojiConfig(
    val categoryAppearance: EmojiCategoryAppearance = EmojiCategoryAppearance.SYMBOL,
    val emojiProvider: EmojiProvider = EmojiProvider.GOOGLE
) : BaseConfigs()