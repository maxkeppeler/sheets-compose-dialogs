@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.info.models.InfoSelection
import com.maxkeppeler.sheets.info.views.BodyComponent

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun InfoView(
    selection: InfoSelection,
    onCancel: () -> Unit = {},
    header: Header? = null,
    body: Body,
) {
    Column(modifier = Modifier.wrapContentHeight()) {

        HeaderComponent(header)

        BodyComponent(body)

        ButtonComponent(
            negativeButtonText = selection.negativeButtonText,
            positiveButtonText = selection.positiveButtonText,
            onNegative = {
                selection.onNegativeClick?.invoke()
                onCancel()
            },
            onPositive = {
                selection.onPositiveClick()
                onCancel()
            }
        )
    }
}

