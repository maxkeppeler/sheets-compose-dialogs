package com.maxkeppeler.sheets.state.views

import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.state.models.State

@Composable
internal fun StateComponent(state: State) {
    state.preView?.invoke()
    StateLabel(state)
    when (state) {
        is State.Loading -> StateLoadingComponent(state)
        is State.Failure -> StateFailureComponent(state)
        is State.Success -> StateSuccessComponent(state)
    }
    state.postView?.invoke()
}