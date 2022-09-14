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
package com.maxkeppeler.sheets.duration.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.maxkeppeler.sheets.core.R as RC

/**
 * The value component that reflects one unit and its value.
 * @param valuePairs The list of value pairs that will be displayed.
 * @param indexOfFirstValue The index of the first valid value.
 * @param hintView If the current component will be displays as a small hint or not.
 */
@Composable
internal fun TimeValueComponent(
    valuePairs: List<Pair<String, String>>,
    indexOfFirstValue: Int? = null,
    hintView: Boolean = false
) {

    val containerModifier = if (hintView) Modifier.wrapContentWidth() else Modifier.fillMaxWidth()

    Row(
        modifier = containerModifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {

        valuePairs.forEachIndexed { index, valuePair ->

            val valeTextStyle = when (hintView) {
                true -> MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
                false -> MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            }

            val labelTextStyle = when (hintView) {
                true -> MaterialTheme.typography.labelMedium
                false -> MaterialTheme.typography.labelLarge
            }

            val spanStyle = labelTextStyle.toSpanStyle().copy()
            val hasValue = indexOfFirstValue?.let { index >= it } ?: false

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = dimensionResource(RC.dimen.scd_small_75)),
                text = buildAnnotatedString {
                    append(valuePair.first)
                    withStyle(spanStyle) {
                        append(" ")
                        append(valuePair.second)
                    }
                },
                style = valeTextStyle.copy(
                    color = if (hasValue) MaterialTheme.colorScheme.primary else valeTextStyle.color
                )
            )
        }

    }
}