@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListConfig
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.*
import com.mk.sheets.compose.R

@Composable
internal fun ListSample1(closeSelection: () -> Unit) {

    val options = listOf(
        ListOption(
            IconSource(R.drawable.ic_fruit_apple),
            titleText = "Apple"
        ),
        ListOption(
            IconSource(R.drawable.ic_fruit_watermelon),
            titleText = "Watermelon",
        ),
        ListOption(
            IconSource(R.drawable.ic_fruit_grapes),
            titleText = "Grapes",
            selected = true
        ),
        ListOption(
            IconSource(R.drawable.ic_fruit_pineapple),
            titleText = "Pineapple",
        ),
    )

    ListDialog(
        show = true,
        selection = ListSelection.Single(
            showRadioButtons = true,
            options = options
        ) { index, option ->
            // Handle selection
        },
        onClose = { closeSelection() }
    )
}