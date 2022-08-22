@file:OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.clock_time.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.clock_time.R

@Composable
internal fun TimeTypeItemComponent(
    modifier: Modifier,
    selected: Boolean,
    text: String,
    onClick: () -> Unit
) {

    val backgroundColor = if (selected) MaterialTheme.colorScheme.secondaryContainer
    else Color.Transparent

    val textStyle =
        if (selected) MaterialTheme.typography.labelSmall
            .copy(color = MaterialTheme.colorScheme.onSecondaryContainer)
        else MaterialTheme.typography.labelMedium
            .copy(color = MaterialTheme.colorScheme.onSurface)

    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .clickable { onClick.invoke() }
            .padding(8.dp)
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

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
                        .padding(horizontal = 6.dp),
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
                            .padding(horizontal = 4.dp),
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
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimeTypeItemComponent(
                    modifier = Modifier,
                    selected = isAm,
                    onClick = { onAm.invoke(true) },
                    text = stringResource(id = R.string.sheets_am),
                )
                TimeTypeItemComponent(
                    modifier = Modifier,
                    selected = !isAm,
                    onClick = { onAm.invoke(false) },
                    text = stringResource(id = R.string.sheets_pm),
                )
            }
        }
    }
}