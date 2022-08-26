package com.maxkeppeler.sheets.option.views


import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.maxkeppeker.sheets.core.views.IconComponent
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.option.models.Option

@Composable
internal fun OptionGridItemComponent(
    option: Option,
    modifier: Modifier,
    iconColor: Color,
    textColor: Color,
    size: MutableState<Size?>?,
    onInfoClick: () -> Unit,
) {

    val textModifier = Modifier.padding(horizontal = 6.dp)

    val standardModifier = Modifier
        .wrapContentHeight()
        .onGloballyPositioned { coordinates ->
            size?.let {
                if ((size.value?.width ?: 0f) <= coordinates.size.width
                    || (size.value?.height ?: 0f) <= coordinates.size.height
                ) {
                    size.value = coordinates.size.toSize()
                }
            }
        }

    val fixedModifier = size?.value?.let {
        standardModifier.size(
            width = (LocalDensity.current.run { it.width.toDp() }),
            height = (LocalDensity.current.run { it.height.toDp() })
        )
    }

    Box(modifier = fixedModifier ?: standardModifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center
        ) {
            option.customView?.invoke(option.selected) ?: run {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    option.icon?.let {
                        IconComponent(
                            modifier = Modifier.size(dimensionResource(R.dimen.size_150)),
                            imageSource = it,
                            tint = iconColor
                        )
                    }
                    Text(
                        maxLines = 1,
                        modifier = textModifier.padding(top = 8.dp),
                        text = option.titleText ?: "",
                        style = MaterialTheme.typography.labelLarge.copy(color = textColor)
                    )
                    option.subtitleText?.let { text ->
                        Text(
                            maxLines = 1,
                            modifier = textModifier,
                            text = text,
                            style = MaterialTheme.typography.bodySmall.copy(color = textColor)
                        )
                    }
                }
            }
        }

        InfoContainerComponent(
            modifier = Modifier.align(Alignment.TopEnd),
            optionInfo = option.details,
            onClick = onInfoClick
        )
    }
}


