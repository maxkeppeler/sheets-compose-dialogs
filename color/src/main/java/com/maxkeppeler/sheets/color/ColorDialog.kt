@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.color

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.models.ColorSelection

/**
 * Color view for the use-case to to select a color.
 * @param show If the dialog should be displayed or not.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param onClose Listener that is invoked to indicate that the use-case is done and the view should be closed.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun ColorDialog(
    show: Boolean,
    selection: ColorSelection,
    config: ColorConfig = ColorConfig(),
    header: Header? = null,
    onClose: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        onClose = onClose,
        properties = properties,
    ) {
        ColorView(
            config = config,
            selection = selection,
            header = header,
            onCancel = onClose
        )
    }
}