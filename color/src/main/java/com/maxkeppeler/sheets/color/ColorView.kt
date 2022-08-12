@file:OptIn(
    ExperimentalSnapperApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.maxkeppeler.sheets.color

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.ButtonComponent
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.color.models.ColorConfig
import com.maxkeppeler.sheets.color.models.ColorSelection
import com.maxkeppeler.sheets.color.models.ColorSelectionMode
import com.maxkeppeler.sheets.color.views.ColorCustomComponent
import com.maxkeppeler.sheets.color.views.ColorSelectionModeComponent
import com.maxkeppeler.sheets.color.views.ColorTemplateComponent
import dev.chrisbanes.snapper.ExperimentalSnapperApi

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun ColorView(
    selection: ColorSelection,
    config: ColorConfig = ColorConfig(),
    onCancel: () -> Unit = {},
    header: Header? = null,
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

    Column(modifier = Modifier.wrapContentHeight()) {

        HeaderComponent(header)

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {

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
        }

        ButtonComponent(
            negativeButtonText = selection.negativeButtonText,
            positiveButtonText = selection.positiveButtonText,
            onPositiveValid = isValid,
            onNegative = onCancel,
            onPositive = { onInvokeListener(); onCancel() }
        )
    }
}

