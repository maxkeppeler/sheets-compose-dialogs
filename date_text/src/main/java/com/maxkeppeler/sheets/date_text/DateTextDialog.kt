@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.date_text

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.date_text.models.DateTextConfig
import com.maxkeppeler.sheets.date_text.models.DateTextSelection

@Composable
fun DateTextDialog(
    show: MutableState<Boolean>,
    selection: DateTextSelection,
    config: DateTextConfig = DateTextConfig(),
    header: Header? = null,
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        DateTextView(
            selection = selection,
            config = config,
            header = header,
            onCancel = { show.value = false }
        )
    }
}