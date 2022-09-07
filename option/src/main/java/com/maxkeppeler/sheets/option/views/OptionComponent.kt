package com.maxkeppeler.sheets.option.views


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.core.R as RC

/**
 * The view for the selection of the options.
 * @param modifier The modifier that is applied to this component.
 * @param config The general configuration.
 * @param options The list of options.
 * @param onOptionChange Listener that is invoked when the state of an option changes.
 */
@Composable
fun OptionComponent(
    modifier: Modifier,
    config: OptionConfig,
    options: List<Option>,
    onOptionChange: (Option) -> Unit,
) {

    val lazyContainerModifier = modifier
        .fillMaxWidth()
        .padding(
            horizontal = dimensionResource(RC.dimen.scd_normal_100),
            vertical = dimensionResource(RC.dimen.scd_normal_100)
        )

    val columnsLimits = config.gridColumns
    val columns = if (options.size < columnsLimits) options.size else columnsLimits

    val onClick: (Option) -> Unit = { option ->
        val newOption = option.copy(selected = !option.selected).apply {
            position = option.position
        }
        onOptionChange(newOption)
    }

    val size = rememberSaveable { mutableStateOf<Size?>(null) }

    when (config.mode) {
        DisplayMode.GRID_HORIZONTAL -> {
            LazyRow(
                modifier = Modifier.padding(
                    top = dimensionResource(RC.dimen.scd_normal_100)
                ),
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(RC.dimen.scd_normal_100)
                )
            ) {
                items(options) { option ->
                    OptionItemComponent(
                        option = option,
                        onClick = onClick,
                        size = size,
                    )
                }
            }
        }
        DisplayMode.GRID_VERTICAL -> {
            LazyVerticalGrid(
                modifier = lazyContainerModifier,
                columns = GridCells.Fixed(columns),
            ) {
                items(options) { option ->
                    OptionItemComponent(
                        option = option,
                        onClick = onClick,
                        size = size,
                    )
                }
            }
        }
        DisplayMode.LIST -> {
            LazyColumn(
                modifier = lazyContainerModifier,
            ) {
                items(options) { option ->
                    OptionItemComponent(
                        option = option,
                        onClick = onClick,
                        grid = false
                    )
                }
            }
        }
    }
}