@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.emoji

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiSelection

/**
 * Emoji dialog for the use-case to to select any emoji or a variant, if available.
 * @param show If the dialog should be displayed or not.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param onClose Listener that is invoked to indicate that the use-case is done and the view should be closed.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun EmojiDialog(
    show: Boolean,
    selection: EmojiSelection,
    config: EmojiConfig = EmojiConfig(),
    header: Header? = null,
    onClose: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        onClose = onClose,
        properties = properties,
    ) {
        EmojiView(
            selection = selection,
            config = config,
            header = header,
            onCancel = onClose
        )
    }
}