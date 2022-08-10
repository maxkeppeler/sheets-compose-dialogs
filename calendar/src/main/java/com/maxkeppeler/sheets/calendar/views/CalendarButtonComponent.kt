package com.maxkeppeler.sheets.calendar.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.core.R as RC

@ExperimentalMaterial3Api
@Composable
internal fun CalendarButtonComponent(
    selection: CalendarSelection,
    onPositive: () -> Unit,
    onNegative: () -> Unit,
    onPositiveValid: () -> Boolean
) {

    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        TextButton(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = dimensionResource(id = RC.dimen.normal_100)),
            onClick = onNegative
        ) {
            Text(
                text = selection.negativeButtonText ?: stringResource(id = RC.string.cancel),
            )
        }

        val valid = onPositiveValid()
        TextButton(
            modifier = Modifier.wrapContentWidth(),
            onClick = onPositive,
            enabled = valid
        ) {
            Text(text = selection.positiveButtonText ?: stringResource(id = RC.string.ok))
        }
    }
}