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
@file:OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.number.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun PortraitNumberValueComponent(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    valueIndex: Int,
    groupIndex: Int,
    unitValues: List<String>,
    onGroupClick: (Int) -> Unit,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = verticalArrangement,
    ) {

        Row(
            modifier = Modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            unitValues.forEachIndexed { currentGroupIndex, value ->

                val textStyle =
                    MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)

                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(MaterialTheme.shapes.medium)
                        .background(if (currentGroupIndex == groupIndex) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent)
                        .clickable { onGroupClick.invoke(currentGroupIndex) }
                        .padding(horizontal = dimensionResource(RC.dimen.scd_small_75)),
                    text = buildAnnotatedString {
                        val values = value.toString().toCharArray()
                        val selectedStyle = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                        )
                        values.forEachIndexed { currentValueIndex, value ->
                            val selected = currentGroupIndex == groupIndex
                                    && currentValueIndex == valueIndex
                            if (selected) withStyle(selectedStyle) { append(value) }
                            else append(value)
                        }
                    },
                    style = textStyle
                )

                if (currentGroupIndex != unitValues.lastIndex) {
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = dimensionResource(RC.dimen.scd_small_50)),
                        text = ",",
                        style = textStyle
                    )
                }
            }
        }
    }
}


@Composable
internal fun LandscapeNumberValueComponent(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    valueIndex: Int,
    groupIndex: Int,
    unitValues: List<String>,
    onGroupClick: (Int) -> Unit,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = verticalArrangement,
    ) {

        Column(
            modifier = Modifier
                .wrapContentWidth(),
            verticalArrangement = Arrangement.Center
        ) {

            unitValues.forEachIndexed { currentGroupIndex, value ->

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val textStyle =
                        MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)

                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(MaterialTheme.shapes.medium)
                            .background(if (currentGroupIndex == groupIndex) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent)
                            .clickable { onGroupClick.invoke(currentGroupIndex) }
                            .padding(horizontal = dimensionResource(RC.dimen.scd_small_75)),
                        text = buildAnnotatedString {
                            val values = value.toString().toCharArray()
                            val selectedStyle = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                            )
                            values.forEachIndexed { currentValueIndex, value ->
                                val selected = currentGroupIndex == groupIndex
                                        && currentValueIndex == valueIndex
                                if (selected) withStyle(selectedStyle) { append(value) }
                                else append(value)
                            }
                        },
                        style = textStyle
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(RC.dimen.scd_small_150)))

                    Text(
                        text = " ",
                        style = MaterialTheme.typography.labelSmall,
                    )

                }
            }
        }
    }
}