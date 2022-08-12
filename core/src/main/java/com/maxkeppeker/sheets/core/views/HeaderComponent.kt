package com.maxkeppeker.sheets.core.views

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeler.sheets.core.R as RC

@ExperimentalMaterial3Api
@Composable
fun HeaderComponent(
    header: Header?,
) {
    when (header) {
        is Header.Custom -> header.header.invoke()
        is Header.Default -> CustomHeaderComponent(header)
        null -> Unit
    }
}

@Composable
private fun CustomHeaderComponent(header: Header.Default) {
    Column {
        Text(
            text = header.titleText,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = RC.dimen.normal_150))
                .padding(top = dimensionResource(id = RC.dimen.normal_150)),
        )
        if (header.subtitleText != null)
            Text(
                text = header.subtitleText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = RC.dimen.normal_150))
                    .padding(top = dimensionResource(id = RC.dimen.small_50)),
            )
    }
}