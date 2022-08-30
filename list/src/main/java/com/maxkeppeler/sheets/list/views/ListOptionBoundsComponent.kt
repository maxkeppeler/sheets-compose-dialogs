package com.maxkeppeler.sheets.list.views


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection


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
                    .padding(horizontal = 24.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                selection.minChoices?.let { minChoices ->
                    if (selectedAmount < minChoices) {
                        Text(
                            text = "Select at least $minChoices options",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                selection.maxChoices?.let { maxChoices ->
                    if (selectedAmount > maxChoices) {
                        Text(
                            text = "Select at most $maxChoices options",
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



