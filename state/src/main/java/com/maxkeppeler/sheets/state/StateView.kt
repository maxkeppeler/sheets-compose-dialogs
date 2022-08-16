@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.state.models.StateSelection

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun StateView(
    selection: StateSelection,
    config: StateConfig,
    onCancel: () -> Unit = {},
    header: Header? = null,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        HeaderComponent(header)

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StateComponent(state = config.state)
        }
    }
}

@Composable
private fun StateComponent(state: State) {
    state.preView?.invoke()
    StateLabel(state)
    when (state) {
        is State.Loading -> StateLoadingComponent(state)
        is State.Failure -> StateFailureComponent(state)
        is State.Success -> StateSuccessComponent(state)
    }
    state.postView?.invoke()
}

@Composable
private fun StateFailureComponent(state: State.Failure) {
    state.customView?.invoke() ?: run {
        Icon(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.normal_100))
                .size(dimensionResource(id = R.dimen.size_350)),
            imageVector = Icons.Rounded.Check,
            contentDescription = null,
            tint = Color.Green
        )
    }
}

@Composable
private fun StateSuccessComponent(state: State.Success) {
    state.customView?.invoke() ?: run {
        Icon(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.normal_100))
                .size(dimensionResource(id = R.dimen.size_350)),
            imageVector = Icons.Rounded.Error,
            contentDescription = null,
            tint = Color.Red
        )
    }
}

@Composable
private fun StateLoadingComponent(state: State.Loading) {
    when (val indicator = state.indicator) {
        is ProgressIndicator.Circular -> CircularProgressIndicator(indicator)
        is ProgressIndicator.Linear -> LinearProgressIndicator(indicator)
        else -> state.customView?.invoke()
    }
}

@Composable
private fun StateLabel(state: State) {
    state.labelText?.let { label ->
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
private fun LinearProgressIndicator(
    indicator: ProgressIndicator.Linear,
) {

    val linearIndicatorModifier = Modifier
        .padding(24.dp)

    indicator.customProgressIndicator?.invoke(indicator.value!!)
        ?: indicator.customIndicator?.invoke()
        ?: indicator.value?.let { progress ->
            if (indicator.showProgressPercentage) {
                Text(
                    text = "${progress.times(100)} %",
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            LinearProgressIndicator(
                progress = progress,
                modifier = linearIndicatorModifier
            )
        } ?: run {
            LinearProgressIndicator(
                modifier = linearIndicatorModifier
            )
        }
}

@Composable
private fun CircularProgressIndicator(
    indicator: ProgressIndicator.Circular,
) {
    val circularIndicatorModifier = Modifier
        .padding(24.dp)
        .width(64.dp)
        .aspectRatio(1f)

    indicator.customProgressIndicator?.invoke(indicator.value!!)
        ?: indicator.customIndicator?.invoke()
        ?: indicator.value?.let { progress ->
            Box(modifier = Modifier.wrapContentSize()) {
                CircularProgressIndicator(
                    progress = progress,
                    modifier = circularIndicatorModifier.align(Alignment.Center)
                )
                if (indicator.showProgressPercentage) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "${progress.times(100)} %",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        } ?: run {
            CircularProgressIndicator(
                modifier = circularIndicatorModifier
            )
        }
}

