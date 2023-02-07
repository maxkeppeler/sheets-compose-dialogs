/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeler.sheets.duration.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeler.sheets.duration.utils.Label
import com.maxkeppeler.sheets.core.R as RC

/**
 * The value component that reflects one unit and its value.
 * @param modifier The modifier that is applied to this component.
 * @param orientation The orientation of the view.
 * @param values The list of value pairs that will be displayed.
 * @param valid If the current value is valid.
 * @param indexOfFirstValue The index of the first valid value.
 * @param isHintView If the current component will be displays as a small hint or not.
 */
@Composable
internal fun TimeValueComponent(
    modifier: Modifier = Modifier,
    orientation: LibOrientation,
    values: List<Pair<String, Label>>,
    valid: Boolean = true,
    indexOfFirstValue: Int? = null,
    hintValue: String? = null,
    hintView: (@Composable () -> Unit)? = null,
) {

    val isHintView = hintValue != null
    val valueContent = @Composable {

        values.forEachIndexed { index, valuePair ->

            val valueStyle = when (orientation) {
                LibOrientation.PORTRAIT -> when (isHintView) {
                    true -> MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    false -> MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
                }
                LibOrientation.LANDSCAPE -> when (isHintView) {
                    true -> MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    false -> MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
                }
            }

            val labelStyle = when (orientation) {
                LibOrientation.PORTRAIT -> when (isHintView) {
                    true -> MaterialTheme.typography.labelMedium
                    false -> MaterialTheme.typography.labelLarge
                }
                LibOrientation.LANDSCAPE -> when (isHintView) {
                    true -> MaterialTheme.typography.labelSmall
                    false -> MaterialTheme.typography.labelSmall
                }
            }


            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = when {
                    orientation == LibOrientation.LANDSCAPE && !isHintView -> Alignment.CenterVertically
                    else -> Alignment.Bottom
                }
            ) {

                hintValue?.let {
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = hintValue,
                        style = labelStyle
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                val hasValue = indexOfFirstValue?.let { index >= it } ?: false
                val valueColor =
                    if (hasValue) MaterialTheme.colorScheme.primary else valueStyle.color
                Text(
                    modifier = if (orientation == LibOrientation.LANDSCAPE) Modifier else Modifier.alignByBaseline(),
                    text = buildAnnotatedString {
                        withStyle(valueStyle.copy(valueColor).toSpanStyle()) {
                            append(valuePair.first)
                        }
                    },
                    style = valueStyle
                )

                Spacer(
                    modifier = Modifier.width(
                        when {
                            orientation == LibOrientation.LANDSCAPE && !isHintView ->
                                dimensionResource(RC.dimen.scd_small_150)
                            else -> dimensionResource(RC.dimen.scd_small_50)
                        }
                    )
                )

                Text(
                    modifier = if (orientation == LibOrientation.LANDSCAPE) Modifier else Modifier.alignByBaseline(),
                    text = when {
                        orientation == LibOrientation.LANDSCAPE && !isHintView ->
                            stringResource(valuePair.second.long)
                        else -> stringResource(valuePair.second.short)
                    },
                    style = labelStyle,
                )
                if (!isHintView && index != values.lastIndex) {
                    Spacer(modifier = Modifier.width(dimensionResource(RC.dimen.scd_small_75)))
                }
            }
        }
    }

    when (orientation) {
        LibOrientation.PORTRAIT -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                content = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row { valueContent() }
                        if (valid) Spacer(modifier = Modifier.height(dimensionResource(RC.dimen.scd_normal_100)))
                        hintView?.invoke()
                    }
                }
            )

        }
        LibOrientation.LANDSCAPE -> {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                content = {
                    valueContent()
//                    Spacer(modifier = Modifier.height(dimensionResource(RC.dimen.scd_normal_100)))
                    hintView?.invoke()
                }
            )
        }
    }

}