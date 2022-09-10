@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.option

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection

/**
 * Option dialog for the use-case to display a list or grid of options.
 * @param show If the dialog should be displayed or not.
 * @param selection The selection configuration for the dialog.
 * @param config The general configuration for the dialog.
 * @param header The header to be displayed at the top of the dialog.
 * @param properties DialogProperties for further customization of this dialog's behavior.
 */
@ExperimentalMaterial3Api
@Composable
fun OptionDialog(
    show: Boolean,
    selection: OptionSelection,
    config: OptionConfig = OptionConfig(),
    header: Header? = null,
    onClose: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        onClose = onClose,
        properties = properties,
    ) {
        OptionView(
            selection = selection,
            config = config,
            header = header,
            onCancel = onClose
        )
    }
}