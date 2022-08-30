@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeker.sheets.core

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.views.base.DialogBase

@ExperimentalMaterial3Api
@Composable
fun CoreDialog(
    show: MutableState<Boolean>,
    selection: BaseSelection,
    header: Header? = null,
    body: @Composable () -> Unit,
    onPositiveValid: () -> Boolean = { true },
    properties: DialogProperties = DialogProperties(),
) {

    DialogBase(
        show = show,
        properties = properties,
    ) {
        CoreView(
            selection = selection,
            header = header,
            body = body,
            onCancel = { show.value = false },
            onPositiveValid = onPositiveValid
        )
    }
}