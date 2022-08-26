package com.maxkeppeler.sheets.option.views


import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.views.IconComponent
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.option.models.Option


@Composable
internal fun OptionListItemComponent(
    option: Option,
    modifier: Modifier,
    iconColor: Color,
    textColor: Color,
    onInfoClick: () -> Unit,
) {

    Box {
        Column(modifier = modifier) {
            option.preView?.invoke(option.selected)
            option.customView?.invoke(option.selected) ?: run {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    option.icon?.let {
                        IconComponent(
                            modifier = Modifier
                                .padding(start = 24.dp)
                                .size(dimensionResource(R.dimen.size_150)),
                            imageSource = it,
                            tint = iconColor
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(bottom = 16.dp)
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    ) {

                        Text(
                            maxLines = 1,
                            text = option.titleText ?: "",
                            style = MaterialTheme.typography.labelLarge.copy(color = textColor)
                        )
                        option.subtitleText?.let { text ->
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodySmall.copy(color = textColor)
                            )
                        }
                    }
                }
            }
            option.postView?.invoke(option.selected)
        }

        InfoContainerComponent(
            modifier = Modifier.align(Alignment.TopEnd),
            optionInfo = option.details,
            onClick = onInfoClick
        )
    }
}
