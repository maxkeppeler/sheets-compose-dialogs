package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.maxkeppeler.sheets.core.R as RC

/**
 * The control item component to build up a value of a custom color.
 * @param label The label text of the color value.
 * @param value The value of the color value.
 * @param onValueChange The listener that returns the new value of the color value.
 * @param colorItemLabelWidth The width of the label.
 * @param colorValueLabelWidth The width of the value.
 */
@Composable
internal fun ColorCustomControlItemComponent(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    colorItemLabelWidth: MutableState<Int?>,
    colorValueLabelWidth: MutableState<Int?>
) {

    val wrapOrFixedModifier: @Composable (MutableState<Int?>) -> Modifier = { stateWidth ->
        val defaultModifier = Modifier
            .wrapContentWidth()
            .onGloballyPositioned { coordinates ->
                if ((stateWidth.value ?: 0) < coordinates.size.width) {
                    stateWidth.value = coordinates.size.width
                }
            }
        stateWidth.value?.let {
            Modifier.width(LocalDensity.current.run { it.toDp() })
        } ?: defaultModifier
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = wrapOrFixedModifier(colorItemLabelWidth),
            text = label,
            style = MaterialTheme.typography.labelMedium,
        )

        Slider(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(RC.dimen.scd_normal_100)),
            valueRange = 0f..255f,
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
        )

        Text(
            modifier = wrapOrFixedModifier(colorValueLabelWidth),
            text = value.toString(),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.End,
        )
    }
}
