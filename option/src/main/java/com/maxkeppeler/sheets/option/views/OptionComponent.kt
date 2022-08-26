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
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import com.maxkeppeler.sheets.option.utils.Constants

@Composable
fun OptionComponent(
    modifier: Modifier,
    selection: OptionSelection,
    config: OptionConfig,
    options: List<Option>,
    onOptionChange: (Int, Option) -> Unit,
) {

    val lazyContainerModifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp)

    val columnsLimits = config.gridColumns ?: Constants.GRID_COLUMNS_MAX_DEFAULT
    val columns = if (options.size < columnsLimits) options.size else columnsLimits

    val onClick: (Option) -> Unit = { option ->
        val index = options.indexOf(option)
        val newOption = option.copy(selected = !option.selected)
        onOptionChange(index, newOption)
    }
    val size = rememberSaveable { mutableStateOf<Size?>(null) }

    when (config.style) {
        DisplayMode.GRID_HORIZONTAL -> {
            LazyRow(
                modifier = Modifier.padding(top = 16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
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