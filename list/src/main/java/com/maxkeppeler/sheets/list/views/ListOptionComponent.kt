package com.maxkeppeler.sheets.list.views


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * The view for the selection of the options.
 * @param modifier The modifier that is applied to this component.
 * @param selection The selection configuration.
 * @param options The list of options.
 * @param inputDisabled If input is disabled.
 * @param onOptionChange Listener that is invoked when the state of an option changes.
 */
@Composable
fun ListOptionComponent(
    modifier: Modifier,
    selection: ListSelection,
    options: List<ListOption>,
    inputDisabled: Boolean,
    onOptionChange: (ListOption) -> Unit,
) {

    val lazyContainerModifier = modifier
        .fillMaxWidth()
        .padding(
            horizontal = dimensionResource(id = RC.dimen.scd_normal_100),
            vertical = dimensionResource(id = RC.dimen.scd_normal_100)
        )

    val onClick: (ListOption) -> Unit = { option ->
        val newOption = option.copy(selected = !option.selected).apply {
            position = option.position
        }
        onOptionChange(newOption)
    }

    LazyColumn(
        modifier = lazyContainerModifier,
    ) {
        items(options) { option ->
            ListOptionItemComponent(
                selection = selection,
                option = option,
                inputDisabled = inputDisabled,
                onClick = onClick,
            )
        }
    }
}