@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.color

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.ButtonsComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.models.ColorSelection
import com.maxkeppeler.sheets.color.models.ColorSelectionMode
import com.maxkeppeler.sheets.color.views.ColorCustomComponent
import com.maxkeppeler.sheets.color.views.ColorSelectionModeComponent
import com.maxkeppeler.sheets.color.views.ColorTemplateComponent

/**
 * Color view for the use-case to to select a color.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterial3Api
@Composable
fun ColorView(
    selection: ColorSelection,
    config: ColorConfig = ColorConfig(),
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    val context = LocalContext.current
    val color = rememberSaveable { mutableStateOf(selection.selectedColor?.colorInInt(context)) }
    val colors = rememberSaveable { mutableStateOf(config.templateColors.getColorsAsInt(context)) }
    var selectedDisplayMode by rememberSaveable {
        val value = config.defaultDisplayMode.takeUnless {
            config.displayMode != null && config.displayMode != config.defaultDisplayMode
        } ?: config.displayMode ?: ColorSelectionMode.TEMPLATE
        mutableStateOf(value)
    }
    val onInvokeListener = { selection.onSelectColor(color.value!!) }
    val onColorClickHandler: (Int) -> Unit = { color.value = it }
    val isValid: () -> Boolean = { color.value != null }

    FrameBase(
        header = { HeaderComponent(header) },
        content = {
            ColorSelectionModeComponent(
                config = config,
                selection = selection,
                mode = selectedDisplayMode,
                onModeChange = { selectedDisplayMode = it },
                onNoColorClick = {
                    selection.onSelectNone?.invoke()
                    onCancel()
                }
            )
            when (selectedDisplayMode) {
                ColorSelectionMode.TEMPLATE ->
                    ColorTemplateComponent(
                        colors = colors.value,
                        selectedColor = color.value,
                        onColorClick = onColorClickHandler
                    )
                ColorSelectionMode.CUSTOM ->
                    ColorCustomComponent(
                        color = color.value ?: Color.Gray.toArgb(),
                        onColorChange = onColorClickHandler,
                    )
            }
        },
        buttons = {
            ButtonsComponent(
                negativeButton = selection.negativeButton,
                positiveButton = selection.positiveButton,
                onPositiveValid = isValid,
                onNegative = {
                    selection.onNegativeClick?.invoke()
                    onCancel()
                },
                onPositive = {
                    onInvokeListener()
                    onCancel()
                }
            )
        }
    )
}

