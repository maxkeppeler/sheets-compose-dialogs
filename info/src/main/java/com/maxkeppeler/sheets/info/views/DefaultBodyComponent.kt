package com.maxkeppeler.sheets.info.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.core.R as RC

/**
 * The default body component for the info dialog.
 * @param body The data of the default body.
 */
@Composable
internal fun DefaultBodyComponent(body: Body.Default) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = RC.dimen.scd_normal_150))
            .padding(top = dimensionResource(id = RC.dimen.scd_normal_100))
    ) {
        body.preBody()

        Text(
            text = body.bodyText,
            style = MaterialTheme.typography.bodyMedium
        )

        body.postBody()
    }
}