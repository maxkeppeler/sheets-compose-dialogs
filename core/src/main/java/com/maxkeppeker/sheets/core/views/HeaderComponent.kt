package com.maxkeppeker.sheets.core.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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
    Row {
        header.icon?.let {
            IconComponent(
                modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                iconSource = it,
            )
        }
        Column {
            Text(
                text = header.titleText,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = RC.dimen.scd_normal_150))
                    .padding(top = dimensionResource(id = RC.dimen.scd_normal_150)),
            )
            if (header.subtitleText != null)
                Text(
                    text = header.subtitleText,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = RC.dimen.scd_normal_150))
                        .padding(top = dimensionResource(id = RC.dimen.scd_small_50)),
                )
        }
    }
}