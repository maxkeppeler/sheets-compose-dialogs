package com.maxkeppeker.sheets.core.views

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
import com.maxkeppeler.sheets.core.R as RC

@ExperimentalMaterial3Api
@Composable
fun ButtonComponent(
    negativeButtonText: String? = null,
    positiveButtonText: String? = null,
    onPositive: () -> Unit,
    onNegative: () -> Unit,
    onPositiveValid: () -> Boolean = { true }
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(horizontal = 16.dp),
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
                text = negativeButtonText ?: stringResource(id = RC.string.cancel),
            )
        }

        val valid = onPositiveValid()
        TextButton(
            modifier = Modifier.wrapContentWidth(),
            onClick = onPositive,
            enabled = valid
        ) {
            Text(text = positiveButtonText ?: stringResource(id = RC.string.ok))
        }
    }
}