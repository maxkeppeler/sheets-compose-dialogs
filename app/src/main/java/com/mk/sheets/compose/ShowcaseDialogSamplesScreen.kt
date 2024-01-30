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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.mk.sheets.compose.models.Sample
import com.mk.sheets.compose.models.UseCaseType
import com.mk.sheets.compose.samples.CalendarSample1
import com.mk.sheets.compose.samples.CalendarSample2
import com.mk.sheets.compose.samples.CalendarSample3
import com.mk.sheets.compose.samples.ClockSample1
import com.mk.sheets.compose.samples.ClockSample2
import com.mk.sheets.compose.samples.ColorSample1
import com.mk.sheets.compose.samples.ColorSample2
import com.mk.sheets.compose.samples.ColorSample3
import com.mk.sheets.compose.samples.CoreSample1
import com.mk.sheets.compose.samples.DateTimeSample1
import com.mk.sheets.compose.samples.DateTimeSample2
import com.mk.sheets.compose.samples.DateTimeSample3
import com.mk.sheets.compose.samples.DurationSample1
import com.mk.sheets.compose.samples.DurationSample2
import com.mk.sheets.compose.samples.EmojiSample1
import com.mk.sheets.compose.samples.EmojiSample2
import com.mk.sheets.compose.samples.InfoSample1
import com.mk.sheets.compose.samples.InputSample1
import com.mk.sheets.compose.samples.InputSample2
import com.mk.sheets.compose.samples.InputSample3
import com.mk.sheets.compose.samples.InputSample4
import com.mk.sheets.compose.samples.ListSample1
import com.mk.sheets.compose.samples.ListSample2
import com.mk.sheets.compose.samples.ListSample3
import com.mk.sheets.compose.samples.ListSample4
import com.mk.sheets.compose.samples.OptionSample1
import com.mk.sheets.compose.samples.OptionSample2
import com.mk.sheets.compose.samples.OptionSample3
import com.mk.sheets.compose.samples.RatingSample1
import com.mk.sheets.compose.samples.RatingSample2
import com.mk.sheets.compose.samples.RatingSample3
import com.mk.sheets.compose.samples.RatingSample4
import com.mk.sheets.compose.samples.StateSample1
import com.mk.sheets.compose.samples.StateSample2
import com.mk.sheets.compose.samples.StateSample3
import com.mk.sheets.compose.samples.StateSample4
import com.mk.sheets.compose.samples.StateSample5
import com.mk.sheets.compose.samples.StateSample6
import com.mk.sheets.compose.samples.StateSample7

@Composable
fun ShowcaseDialogSamplesScreen() {

    val currentSample = rememberSaveable { mutableStateOf<Sample?>(null) }
    val onReset = { currentSample.value = null }
    val onResetSheet: UseCaseState.() -> Unit = { currentSample.value = null }

    ShowcaseSamples(
        modifier = Modifier.alpha(if (currentSample.value != null) 0f else 1f),
        onSelectSample = { currentSample.value = it }
    )

    ShowcaseDialogSamples(
        currentSample = currentSample.value,
        onReset = onReset,
        onResetSheet = onResetSheet
    )
}

@Composable
fun ShowcaseDialogSamples(
    currentSample: Sample?,
    onReset: () -> Unit = {},
    onResetSheet: UseCaseState.() -> Unit = {}
) {
    when (currentSample) {

        Sample.CORE_SAMPLE_1 -> CoreSample1(onReset)
        Sample.INFO_SAMPLE_1 -> InfoSample1(onReset)

        Sample.RATING_SAMPLE_1 -> RatingSample1(onReset)
        Sample.RATING_SAMPLE_2 -> RatingSample2(onReset)
        Sample.RATING_SAMPLE_3 -> RatingSample3(onReset)
        Sample.RATING_SAMPLE_4 -> RatingSample4(onReset)

        Sample.COLOR_SAMPLE_1 -> ColorSample1(onReset)
        Sample.COLOR_SAMPLE_2 -> ColorSample2(onReset)
        Sample.COLOR_SAMPLE_3 -> ColorSample3(onReset)

        Sample.CALENDAR_SAMPLE_1 -> CalendarSample1(onResetSheet)
        Sample.CALENDAR_SAMPLE_2 -> CalendarSample2(onResetSheet)
        Sample.CALENDAR_SAMPLE_3 -> CalendarSample3(onResetSheet)

        Sample.CLOCK_SAMPLE_1 -> ClockSample1(onReset)
        Sample.CLOCK_SAMPLE_2 -> ClockSample2(onReset)

        Sample.DATE_TIME_SAMPLE_1 -> DateTimeSample1(onReset)
        Sample.DATE_TIME_SAMPLE_2 -> DateTimeSample2(onReset)
        Sample.DATE_TIME_SAMPLE_3 -> DateTimeSample3(onReset)

        Sample.DURATION_SAMPLE_1 -> DurationSample1(onReset)
        Sample.DURATION_SAMPLE_2 -> DurationSample2(onReset)

        Sample.OPTION_SAMPLE_1 -> OptionSample1(onReset)
        Sample.OPTION_SAMPLE_2 -> OptionSample2(onReset)
        Sample.OPTION_SAMPLE_3 -> OptionSample3(onReset)

        Sample.LIST_SAMPLE_1 -> ListSample1(onReset)
        Sample.LIST_SAMPLE_2 -> ListSample2(onReset)
        Sample.LIST_SAMPLE_3 -> ListSample3(onReset)
        Sample.LIST_SAMPLE_4 -> ListSample4(onReset)

        Sample.INPUT_SAMPLE_1 -> InputSample1(onReset)
        Sample.INPUT_SAMPLE_2 -> InputSample2(onReset)
        Sample.INPUT_SAMPLE_3 -> InputSample3(onReset)
        Sample.INPUT_SAMPLE_4 -> InputSample4(onReset)

        Sample.EMOJI_SAMPLE_1 -> EmojiSample1(onReset)
        Sample.EMOJI_SAMPLE_2 -> EmojiSample2(onReset)

        Sample.STATE_SAMPLE_1 -> StateSample1(onReset)
        Sample.STATE_SAMPLE_2 -> StateSample2(onReset)
        Sample.STATE_SAMPLE_3 -> StateSample3(onReset)
        Sample.STATE_SAMPLE_4 -> StateSample4(onReset)
        Sample.STATE_SAMPLE_5 -> StateSample5(onReset)
        Sample.STATE_SAMPLE_6 -> StateSample6(onReset)
        Sample.STATE_SAMPLE_7 -> StateSample7(onReset)
        null -> Unit
    }
}

@Composable
internal fun ShowcaseSamples(
    modifier: Modifier,
    onSelectSample: (Sample) -> Unit
) {

    val groupedByType = remember {
        val value = Sample.values().groupBy { it.category }
        mutableStateOf(value)
    }

    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Row(Modifier.padding(top = 8.dp)) {
                Icon(imageVector = Icons.Filled.Info, null)
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Click the samples to interact with the respective dialog.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        groupedByType.value.forEach { type ->
            item(span = { GridItemSpan(2) }) { HeaderItem(type) }
            itemsIndexed(type.value) { i, sample ->
                SampleItem(i, sample, onSelectSample)
            }
        }
    }
}

@Composable
private fun HeaderItem(
    setup: Map.Entry<UseCaseType, List<Sample>>
) {
    Text(
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        text = setup.key.title,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun SampleItem(
    index: Int, sample: Sample,
    onSelectSample: (Sample) -> Unit
) {
    ElevatedCard(
        onClick = { onSelectSample(sample) }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "${index.plus(1)}. Sample",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(12.dp))
            sample.specifics.forEach { info ->
                Row(
                    modifier = Modifier.padding(bottom = if (index < Sample.values().size) 4.dp else 0.dp),
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .size(8.dp),
                        imageVector = Icons.Filled.Circle,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = info,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}