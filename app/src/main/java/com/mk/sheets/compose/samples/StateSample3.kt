@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeler.sheets.emoji.EmojiDialog
import com.maxkeppeler.sheets.emoji.models.EmojiSelection
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.state.models.StateSelection
import kotlinx.coroutines.delay

@Composable
internal fun StateSample3(closeSelection: () -> Unit) {

    val progress = remember { mutableStateOf(0f) }
    val progressAnimated = animateFloatAsState(targetValue = progress.value, tween(1000)).value
    LaunchedEffect(Unit) {
        progress.value = 1f
        delay(1000)
    }

    val state = State.Loading(
        "Wait a moment",
        ProgressIndicator.Circular(progressAnimated)
    )
    StateDialog(
        show = true,
        config = StateConfig(state = state),
        onClose = { closeSelection() }
    )
}