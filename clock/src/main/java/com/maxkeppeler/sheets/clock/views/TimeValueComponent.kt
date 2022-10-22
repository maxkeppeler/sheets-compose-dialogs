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
@file:OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.clock.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags
import com.maxkeppeler.sheets.clock.R
import com.maxkeppeler.sheets.core.R as RC

@Composable
internal fun TimeValueComponent(
    valueIndex: Int,
    groupIndex: Int,
    unitValues: List<StringBuilder>,
    is24hourFormat: Boolean,
    isAm: Boolean,
    onGroupClick: (Int) -> Unit,
    onAm: (Boolean) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(RC.dimen.scd_normal_100)),
        horizontalAlignment = Alignment.CenterHorizontally
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
                        text = ":",
                        style = textStyle
                    )
                }
            }

        }
        if (!is24hourFormat) {
            Row(
                Modifier
                    .padding(top = dimensionResource(RC.dimen.scd_small_50)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimeTypeItemComponent(
                    modifier = Modifier.testTags(TestTags.CLOCK_12_HOUR_FORMAT, 0),
                    selected = isAm,
                    onClick = { onAm.invoke(true) },
                    text = stringResource(id = R.string.scd_clock_dialog_am),
                )
                TimeTypeItemComponent(
                    modifier = Modifier.testTags(TestTags.CLOCK_12_HOUR_FORMAT, 1),
                    selected = !isAm,
                    onClick = { onAm.invoke(false) },
                    text = stringResource(id = R.string.scd_clock_dialog_pm),
                )
            }
        }
    }
}