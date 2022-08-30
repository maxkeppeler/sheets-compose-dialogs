package com.maxkeppeler.sheets.emoji.models

import com.maxkeppeker.sheets.core.models.base.BaseConfigs

/**
 * Available emoji configurations.
 */
data class EmojiConfig(

    /**
     * The style for the category bar.
     */
    val categoryStyle: EmojiCategoryStyle = EmojiCategoryStyle.SYMBOL,

    /**
     *
     */
    val emojiProvider: EmojiProvider = EmojiProvider.GOOGLE

    ) : BaseConfigs()