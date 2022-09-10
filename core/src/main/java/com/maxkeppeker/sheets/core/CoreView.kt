@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeker.sheets.core

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase

/**
 * Core view that functions as the base of a custom use-case.
 * @param selection The selection configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param body The body content to be displayed inside the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 * @param onPositiveValid Listener that is invoked to check if the dialog view input is valid.
 */
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun CoreView(
    selection: CoreSelection,
    header: Header? = null,
    body: @Composable () -> Unit,
    onCancel: () -> Unit,
    onPositiveValid: () -> Boolean = { true }
) {

    FrameBase(
        header = { HeaderComponent(header) },
        content = { body() },
        buttonsVisible = selection.withButtonView,
        buttons = {
            ButtonsComponent(
                onPositiveValid = onPositiveValid,
                negativeButton = selection.negativeButton,
                positiveButton = selection.positiveButton,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    selection.onPositiveClick?.invoke()
                    onCancel()
                }
            )
        }
    )
}

