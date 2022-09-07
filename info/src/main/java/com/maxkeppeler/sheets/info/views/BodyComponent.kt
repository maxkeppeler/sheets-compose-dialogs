package com.maxkeppeler.sheets.info.views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeler.sheets.info.models.Body

/**
 * Body component for the info dialog.
 * @param body Implementation of the body.
 */
@ExperimentalMaterial3Api
@Composable
fun BodyComponent(
    body: Body,
) {
    when (body) {
        is Body.Custom -> body.body.invoke()
        is Body.Default -> DefaultBodyComponent(body)
    }
}