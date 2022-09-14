/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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
import com.maxkeppeler.sheets.core.R as RC
import com.maxkeppeler.sheets.option.models.Option

/**
 * The grid item component for an option.
 * @param option The option that will be displayed.
 * @param modifier The modifier that is applied to this component.
 * @param iconColor The color of the icon.
 * @param textColor The color of the text.
 * @param size The size that should be applied to the option component.
 * @param onInfoClick The listener that is invoked when the info button is clicked.
 */
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
                        .padding(top = dimensionResource(RC.dimen.scd_normal_100))
                        .padding(bottom = dimensionResource(RC.dimen.scd_normal_100))
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    option.icon?.let {
                        IconComponent(
                            modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                            iconSource = it,
                            tint = iconColor
                        )
                    }
                    Text(
                        maxLines = 1,
                        modifier = textModifier.padding(top = 8.dp),
                        text = option.titleText,
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


