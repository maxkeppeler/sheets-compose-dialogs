@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.color.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.graphics.alpha
import com.maxkeppeler.sheets.color.R
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun ColorTemplateItemComponent(
    modifier: Modifier = Modifier,
    color: Int,
    selected: Boolean,
    onColorClick: (Int) -> Unit
) {
    val colorShape = MaterialTheme.shapes.medium
    Box(modifier = modifier.aspectRatio(1f)) {
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.Center)
                .aspectRatio(1f),
            shape = colorShape,
            onClick = { onColorClick(color) }
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.transparent_pattern),
                    contentDescription = null,
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(color))
                ) {
                }
            }
        }
        if (selected) {
            FilledIconButton(
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(dimensionResource(RC.dimen.size_150)),
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier
                        .size(dimensionResource(RC.dimen.size_100)),
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        } else if (color.alpha < 255) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(dimensionResource(id = RC.dimen.small_25)),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall,
                text = "${color.alpha.toFloat().div(255).times(100).toInt()}%"
            )
        }
    }
}