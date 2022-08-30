@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeker.sheets.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun CoreView(
    selection: BaseSelection,
    header: Header? = null,
    body: @Composable () -> Unit,
    onCancel: () -> Unit = {},
    onSelection: () -> Unit = {},
    onPositiveValid: () -> Boolean = { true }
) {
    Column(modifier = Modifier.wrapContentHeight()) {

        HeaderComponent(header)

        Column(modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp)) {
            body()
        }

        if (selection.withButtonView) {
            ButtonComponent(
                onPositiveValid = onPositiveValid,
                negativeButtonText = selection.negativeButtonText,
                positiveButtonText = selection.positiveButtonText,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    onSelection()
                    onCancel()
                }
            )
        }
    }
}

