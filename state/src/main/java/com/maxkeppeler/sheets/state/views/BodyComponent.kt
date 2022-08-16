package com.maxkeppeler.sheets.state.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.state.models.Body
import com.maxkeppeler.sheets.core.R as RC

@ExperimentalMaterial3Api
@Composable
fun BodyComponent(
    body: Body,
) {
    when (body) {
        is Body.Custom -> body.body.invoke()
        is Body.Default -> CustomBodyComponent(body)
    }
}

@Composable
private fun CustomBodyComponent(body: Body.Default) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = RC.dimen.normal_150))
            .padding(top = dimensionResource(id = RC.dimen.normal_100))
    ) {
        body.preBody()

        Text(
            text = body.bodyText,
            style = MaterialTheme.typography.bodyMedium
        )

        body.postBody()
    }
}