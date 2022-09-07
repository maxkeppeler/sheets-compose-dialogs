package com.maxkeppeler.sheets.list.views


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.maxkeppeler.sheets.list.R
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * A view that reflects the selection bounds of the list.
 * @param selection The selection configuration.
 * @param selectedOptions The list of options that are selected.
 */
@Composable
internal fun ListOptionBoundsComponent(
    selection: ListSelection,
    selectedOptions: List<ListOption>
) {
    val selectedAmount = selectedOptions.size

    when (selection) {
        is ListSelection.Single -> Unit
        is ListSelection.Multiple -> {
            Row(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = RC.dimen.scd_normal_150))
                    .padding(top = dimensionResource(id = RC.dimen.scd_normal_100)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                selection.minChoices?.let { minChoices ->
                    if (selectedAmount < minChoices) {
                        Text(
                            text = stringResource(id = R.string.scd_list_dialog_min_choices, minChoices),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                selection.maxChoices?.let { maxChoices ->
                    if (selectedAmount > maxChoices) {
                        Text(
                            text = stringResource(id = R.string.scd_list_dialog_max_choices, maxChoices),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                MaterialTheme.typography.headlineSmall.copy(
                                    letterSpacing = 0.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                ).toSpanStyle()
                            ) {
                                append(selectedAmount.toString())
                            }
                            append("/")
                            append(maxChoices.toString())
                        },
                        style = MaterialTheme.typography.labelLarge.copy(letterSpacing = 0.5.sp)
                    )
                }
            }
        }
    }
}



