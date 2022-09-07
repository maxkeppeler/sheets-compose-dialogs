package com.maxkeppeler.sheets.state.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R as RC
import com.maxkeppeler.sheets.state.models.State

@Composable
internal fun StateSuccessComponent(state: State.Success) {
    state.customView?.invoke() ?: run {
        Icon(
            modifier = Modifier
                .padding(top = dimensionResource(id = RC.dimen.scd_normal_100))
                .size(dimensionResource(id = RC.dimen.scd_size_350)),
            imageVector = Icons.Rounded.Error,
            contentDescription = null,
            tint = Color.Red
        )
    }
}