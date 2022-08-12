package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
internal fun ColorTemplateComponent(
    colors: List<Int>,
    selectedColor: Int?,
    onColorClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(48.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .graphicsLayer { alpha = 0.99F }
            .drawWithContent {
                val colorStops = arrayOf(
                    0.0f to Color.Transparent,
                    0.05f to Color.Black,
                    0.95f to Color.Black,
                    1.0f to Color.Transparent
                )
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(*colorStops),
                    blendMode = BlendMode.DstIn
                )
            },
    ) {
        items(colors) { color ->
            val selected = color == selectedColor
            ColorTemplateItemComponent(
                modifier = Modifier.padding(4.dp),
                color = color,
                selected = selected,
                onColorClick = onColorClick
            )
        }
    }
}