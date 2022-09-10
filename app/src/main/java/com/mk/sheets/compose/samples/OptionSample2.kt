@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.*
import com.mk.sheets.compose.R

@Composable
internal fun OptionSample2(closeSelection: () -> Unit) {

    val options = listOf(
        Option(
            IconSource(R.drawable.ic_fruit_apple),
            titleText = "Apple"
        ),
        Option(
            IconSource(R.drawable.ic_fruit_watermelon),
            titleText = "Watermelon",
            disabled = true
        ),
        Option(
            IconSource(R.drawable.ic_fruit_grapes),
            titleText = "Grapes",
            selected = true
        ),
        Option(
            IconSource(R.drawable.ic_fruit_pineapple),
            titleText = "Pineapple",
        ),
    )

    OptionDialog(
        show = true,
        selection = OptionSelection.Single(options) { index, option ->
            // Handle selection
        },
        config = OptionConfig(mode = DisplayMode.GRID_HORIZONTAL),
        onClose = { closeSelection() }
    )
}