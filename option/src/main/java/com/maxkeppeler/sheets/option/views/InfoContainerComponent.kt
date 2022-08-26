@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.option.views


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.option.models.OptionDetails

@Composable
internal fun InfoContainerComponent(
    modifier: Modifier = Modifier,
    optionInfo: OptionDetails?,
    onClick: () -> Unit
) {

    if (optionInfo == null) return

    Box(modifier = modifier) {

        FilledIconButton(
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.background),
            modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(50))
                .size(dimensionResource(R.dimen.size_150)),
            onClick = { onClick() }
        ) {

            Icon(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.size_100)),
                imageVector = Icons.Rounded.Info,
                contentDescription = null,
            )
        }

    }
}