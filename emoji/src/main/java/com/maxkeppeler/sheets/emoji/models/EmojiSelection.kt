@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.emoji.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection

/**
 * Selection and selection-based configurations.
 */
sealed class EmojiSelection : BaseSelection() {

    class Unicode(
        override val withButtonView: Boolean = true,
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val onPositiveClick: (String) -> Unit,
    ) : EmojiSelection()

    class Emoji(
        override val withButtonView: Boolean = true,
        override val negativeButtonText: String? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButtonText: String? = null,
        val onPositiveClick: (com.vanniktech.emoji.Emoji) -> Unit
    ) : EmojiSelection()

}