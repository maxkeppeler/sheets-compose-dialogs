@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.info

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.info.models.InfoSelection
import com.maxkeppeler.sheets.info.views.BodyComponent

/**
 * Info view for the use-case to display simple information.
 * @param selection The selection configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param body The body content to be displayed inside the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun InfoView(
    selection: InfoSelection,
    header: Header? = null,
    body: Body,
    onCancel: () -> Unit = {},
) {
    FrameBase(
        header = { HeaderComponent(header) },
        content = { BodyComponent(body) },
        buttonsVisible = selection.withButtonView,
        buttons = {
            ButtonsComponent(
                negativeButton = selection.negativeButton,
                positiveButton = selection.positiveButton,
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
    )
}

