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

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .align(Alignment.Center)
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
            Column(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = dimensionResource(RC.dimen.scd_normal_100)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimeTypeItemComponent(
                    modifier = Modifier,
                    selected = isAm,
                    onClick = { onAm.invoke(true) },
                    text = stringResource(id = R.string.scd_clock_dialog_am),
                )
                TimeTypeItemComponent(
                    modifier = Modifier,
                    selected = !isAm,
                    onClick = { onAm.invoke(false) },
                    text = stringResource(id = R.string.scd_clock_dialog_pm),
                )
            }
        }
    }
}