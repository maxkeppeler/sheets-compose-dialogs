package com.maxkeppeker.sheets.core.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeler.sheets.core.R


/**
 * Defines module-wide modifiers.
 */
object BaseModifiers {

    /**
     * Dynamic content in a Column is wrapped and can expand up to a specific height.
     * @receiver modifier Modifier that is used to apply the behavior
     * @param scope The current column scope
     */
    fun Modifier.dynamicContentWrapOrMaxHeight(scope: ColumnScope): Modifier = composed {
        val modifier = this
        val dynamicContentMaxHeight = dimensionResource(id = R.dimen.scd_dynamic_content_max_height)
        scope.run {
            modifier
                .weight(1f, false)
                .heightIn(max = dynamicContentMaxHeight)
        }
    }
}