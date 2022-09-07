package com.maxkeppeker.sheets.core.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.core.R

/**
 * Defines module-wide values.
 */
object BaseValues {

    val CONTENT_DEFAULT_PADDING: PaddingValues
        @Composable
        get() = PaddingValues(
            start = dimensionResource(id = R.dimen.scd_normal_150),
            end = dimensionResource(id = R.dimen.scd_normal_150),
            top = dimensionResource(id = R.dimen.scd_normal_100)
        )
}