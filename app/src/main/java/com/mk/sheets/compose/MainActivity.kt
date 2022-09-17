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
package com.mk.sheets.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.mk.sheets.compose.samples.*
import com.mk.sheets.compose.ui.theme.SheetsComposeTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SheetsComposeTheme {
                androidx.compose.material3.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Samples()
                }
            }
        }
    }
}

@Composable
private fun Samples() {

    val currentSample = rememberSaveable { mutableStateOf<Sample?>(null) }
    val onReset = { currentSample.value = null }
    ShowcaseSamples { currentSample.value = it }

    when (currentSample.value) {

        Sample.CORE_SAMPLE_1 -> CoreSample1(onReset)
        Sample.INFO_SAMPLE_1 -> InfoSample1(onReset)

        Sample.COLOR_SAMPLE_1 -> ColorSample1(onReset)
        Sample.COLOR_SAMPLE_2 -> ColorSample2(onReset)
        Sample.COLOR_SAMPLE_3 -> ColorSample3(onReset)

        Sample.CALENDAR_SAMPLE_1 -> CalendarSample1(onReset)
        Sample.CALENDAR_SAMPLE_2 -> CalendarSample2(onReset)
        Sample.CALENDAR_SAMPLE_3 -> CalendarSample3(onReset)

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