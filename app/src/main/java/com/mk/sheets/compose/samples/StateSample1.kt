@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.delay

@Composable
internal fun StateSample1(closeSelection: () -> Unit) {

    val state = remember {
        val startState =
            State.Loading(labelText = "Fetching new data...", ProgressIndicator.Circular())
        mutableStateOf<State>(startState)
    }
    LaunchedEffect(Unit) {
        delay(2000)
        state.value = State.Failure(labelText = "Fetching data failed. Trying again.")
        delay(2000)
        state.value =
            State.Loading(labelText = "Fetching new data...", ProgressIndicator.Circular())
        delay(2000)
        state.value = State.Success(labelText = "Data fetched..!")
    }

    StateDialog(
        show = true,
        config = StateConfig(state = state.value),
        onClose = { closeSelection() }
    )
}