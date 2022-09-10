@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.*
import com.mk.sheets.compose.R

@Composable
internal fun OptionSample3(closeSelection: () -> Unit) {

    val options = listOf(
        Option(
            IconSource(R.drawable.ic_fruit_apple),
            titleText = "Apple"
        ),
        Option(
            IconSource(R.drawable.ic_fruit_watermelon),
            titleText = "Watermelon",
        ),
        Option(
            IconSource(R.drawable.ic_fruit_grapes),
            titleText = "Grapes",
            selected = true
        ),
        Option(
            IconSource(R.drawable.ic_fruit_pineapple),
            titleText = "Pineapple",
            details = OptionDetails(
                "Ananas comosus",
                "The pineapple is a tropical plant with an edible fruit; it is the most economically significant plant in the family Bromeliaceae."
            )
        ),
        Option(
            IconSource(R.drawable.ic_fruit_cherries),
            titleText = "Cherries",
        ),
        Option(
            IconSource(R.drawable.ic_fruit_citrus),
            titleText = "Citrus",
        ),
    )

    OptionDialog(
        show = true,
        selection = OptionSelection.Multiple(
            minChoices = 2,
            maxChoices = 3,
            options = options
        ) { indicies, options ->
            // Handle selections
        },
        config = OptionConfig(
            mode = DisplayMode.GRID_VERTICAL,
        ),
        onClose = { closeSelection() }
    )
}