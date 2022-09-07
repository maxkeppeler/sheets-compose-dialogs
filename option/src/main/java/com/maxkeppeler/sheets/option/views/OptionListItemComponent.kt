package com.maxkeppeler.sheets.option.views


import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.views.IconComponent
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.core.R as RC


/**
 * The list item component for an option.
 * @param option The option that will be displayed.
 * @param modifier The modifier that is applied to this component.
 * @param iconColor The color of the icon.
 * @param textColor The color of the text.
 * @param onInfoClick The listener that is invoked when the info button is clicked.
 */
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
            option.listTopView?.invoke(option.selected)
            option.customView?.invoke(option.selected) ?: run {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    option.icon?.let {
                        IconComponent(
                            modifier = Modifier
                                .padding(start = dimensionResource(RC.dimen.scd_normal_150))
                                .size(dimensionResource(RC.dimen.scd_size_150)),
                            iconSource = it,
                            tint = iconColor
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(
                                top = dimensionResource(RC.dimen.scd_normal_100),
                                bottom = dimensionResource(RC.dimen.scd_normal_100),
                                start = dimensionResource(RC.dimen.scd_normal_100)
                            )
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    ) {

                        Text(
                            maxLines = 1,
                            text = option.titleText,
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
            option.listBottomView?.invoke(option.selected)
        }

        InfoContainerComponent(
            modifier = Modifier.align(Alignment.TopEnd),
            optionInfo = option.details,
            onClick = onInfoClick
        )
    }
}
