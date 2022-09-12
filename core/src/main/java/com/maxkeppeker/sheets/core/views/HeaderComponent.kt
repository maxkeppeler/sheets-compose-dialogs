package com.maxkeppeker.sheets.core.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeler.sheets.core.R as RC

/**
 * Header component of the dialog.
 * @param header Implementation of the header.
 * @param alternateContent Content when dialog has no header.
 */
@ExperimentalMaterial3Api
@Composable
fun HeaderComponent(
    header: Header?,
    alternateContent: @Composable () -> Unit = {}
) {
    when (header) {
        is Header.Custom -> header.header.invoke()
        is Header.Default -> DefaultHeaderComponent(header)
        null -> alternateContent()
    }
}

/**
 * The default header component for a dialog.
 * @param header The data of the default header.
 */
@Composable
private fun DefaultHeaderComponent(header: Header.Default) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = RC.dimen.scd_normal_150))
            .padding(horizontal = dimensionResource(id = RC.dimen.scd_normal_150)),
        horizontalAlignment = if (header.icon != null) Alignment.CenterHorizontally else Alignment.Start
    ) {
        header.icon?.let {
            IconComponent(
                modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                iconSource = it,
                defaultTint = MaterialTheme.colorScheme.secondary
            )
        }
        Text(
            text = header.title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(
                top = if (header.icon != null) dimensionResource(id = RC.dimen.scd_normal_100)
                else 0.dp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = if (header.icon != null) TextAlign.Center else TextAlign.Start
        )
    }
}