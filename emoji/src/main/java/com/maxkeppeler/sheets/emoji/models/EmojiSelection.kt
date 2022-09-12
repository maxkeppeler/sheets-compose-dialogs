@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package com.maxkeppeler.sheets.emoji.models

import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton

/**
 * The selection configuration for the emoji dialog.
 */
sealed class EmojiSelection : BaseSelection() {

    /**
     * Select the emoji as unicode.
     * @param withButtonView Show the dialog with the buttons view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param onPositiveClick The listener that returns the selected emoji as unicode.
     */
    class Unicode(
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        val onPositiveClick: (String) -> Unit,
    ) : EmojiSelection()

    /**
     * Select the emoji.
     * @param withButtonView Show the dialog with the buttons view.
     * @param extraButton An extra button that can be used for a custom action.
     * @param onExtraButtonClick The listener that is invoked when the extra button is clicked.
     * @param negativeButton The button that will be used as a negative button.
     * @param onNegativeClick The listener that is invoked when the negative button is clicked.
     * @param positiveButton The button that will be used as a positive button.
     * @param onPositiveClick The listener that returns the selected emoji.
     */
    class Emoji(
        override val withButtonView: Boolean = true,
        override val extraButton: SelectionButton? = null,
        override val onExtraButtonClick: (() -> Unit)? = null,
        override val negativeButton: SelectionButton? = null,
        override val onNegativeClick: (() -> Unit)? = null,
        override val positiveButton: SelectionButton? = null,
        val onPositiveClick: (com.vanniktech.emoji.Emoji) -> Unit
    ) : EmojiSelection()

}