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
                    Showcase()
                }
            }
        }
    }
}

@Composable
private fun Showcase() {

    val useCase = rememberSaveable { mutableStateOf<UseCase?>(null) }
    val onReset = { useCase.value = null }
    UseCasesDemo { newUseCase -> useCase.value = newUseCase }

    when (useCase.value) {
        UseCase.CORE_SAMPLE_1 -> CoreSample1(onReset)
        UseCase.INFO_SAMPLE_1 -> InfoSample1(onReset)

        UseCase.COLOR_SAMPLE_1 -> ColorSample1(onReset)
        UseCase.COLOR_SAMPLE_2 -> ColorSample2(onReset)
        UseCase.COLOR_SAMPLE_3 -> ColorSample3(onReset)

        UseCase.CALENDAR_SAMPLE_1 -> CalendarSample1(onReset)
        UseCase.CALENDAR_SAMPLE_2 -> CalendarSample2(onReset)
        UseCase.CALENDAR_SAMPLE_3 -> CalendarSample3(onReset)

        UseCase.CLOCK_SAMPLE_1 -> ClockSample1(onReset)
        UseCase.CLOCK_SAMPLE_2 -> ClockSample2(onReset)

        UseCase.DATE_TIME_SAMPLE_1 -> DateTimeSample1(onReset)
        UseCase.DATE_TIME_SAMPLE_2 -> DateTimeSample2(onReset)
        UseCase.DATE_TIME_SAMPLE_3 -> DateTimeSample3(onReset)

        UseCase.DURATION_SAMPLE_1 -> DurationSample1(onReset)
        UseCase.DURATION_SAMPLE_2 -> DurationSample2(onReset)

        UseCase.OPTION_SAMPLE_1 -> OptionSample1(onReset)
        UseCase.OPTION_SAMPLE_2 -> OptionSample2(onReset)
        UseCase.OPTION_SAMPLE_3 -> OptionSample3(onReset)

        UseCase.LIST_SAMPLE_1 -> ListSample1(onReset)
        UseCase.LIST_SAMPLE_2 -> ListSample2(onReset)
        UseCase.LIST_SAMPLE_3 -> ListSample3(onReset)
        UseCase.LIST_SAMPLE_4 -> ListSample4(onReset)

        UseCase.EMOJI_SAMPLE_1 -> EmojiSample1(onReset)
        UseCase.EMOJI_SAMPLE_2 -> EmojiSample2(onReset)

        UseCase.STATE_SAMPLE_1 -> StateSample1(onReset)
        UseCase.STATE_SAMPLE_2 -> StateSample2(onReset)
        UseCase.STATE_SAMPLE_3 -> StateSample3(onReset)
        UseCase.STATE_SAMPLE_4 -> StateSample4(onReset)
        UseCase.STATE_SAMPLE_5 -> StateSample5(onReset)
        UseCase.STATE_SAMPLE_6 -> StateSample6(onReset)
        UseCase.STATE_SAMPLE_7 -> StateSample7(onReset)
        null -> Unit
    }
}