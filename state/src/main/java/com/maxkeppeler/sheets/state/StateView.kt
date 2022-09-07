@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("UNUSED_PARAMETER")

package com.maxkeppeler.sheets.state

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeker.sheets.core.views.base.FrameBase
import com.maxkeppeler.sheets.core.R as RC
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.state.models.StateSelection
import com.maxkeppeler.sheets.state.views.StateComponent

/**
 * State dialog for the use-case to display various states.
 * @param selection The selection configuration for the dialog view.
 * @param config The general configuration for the dialog view.
 * @param header The header to be displayed at the top of the dialog view.
 * @param onCancel Listener that is invoked when the use-case was canceled.
 */
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun StateView(
    selection: StateSelection,
    config: StateConfig,
    header: Header? = null,
    onCancel: () -> Unit = {},
) {
    FrameBase(
        header = { HeaderComponent(header) },
        contentHorizontalAlignment = Alignment.CenterHorizontally,
        contentPaddingValues = PaddingValues(
            horizontal = dimensionResource(id = RC.dimen.scd_normal_100),
            vertical = dimensionResource(id = RC.dimen.scd_normal_100)
        ),
        content = { StateComponent(state = config.state) },
    )
}





