package com.maxkeppeler.sheets.option.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.views.IconComponent
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.core.R as RC

/**
 * Option dialog for the use-case to display a list or grid of options.
 * @param show The state used to show and hide the dialog.
 * @param option The option that will be displayed.
 * @param backgroundColor The color that is used for the background of the option.
 * @param iconColor The color that is used for the icon of the option.
 * @param textColor The color that is used for the texts of the option.
 */
@Composable
internal fun OptionDetailsDialog(
    show: MutableState<Boolean>,
    option: Option,
    backgroundColor: Color,
    iconColor: Color,
    textColor: Color
) {
    DialogBase(
        show = show,
        onDialogClick = { show.value = !show.value }) {
        Column {
            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(RC.dimen.scd_normal_100))
                    .padding(horizontal = dimensionResource(RC.dimen.scd_normal_100))
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .then(
                        if (option.disabled || option.selected) Modifier.background(
                            backgroundColor
                        ) else Modifier
                    )
                    .padding(dimensionResource(RC.dimen.scd_normal_100)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                option.icon?.let {
                    IconComponent(
                        modifier = Modifier.size(dimensionResource(RC.dimen.scd_size_150)),
                        iconSource = it,
                        tint = iconColor
                    )
                }
                Text(
                    modifier = Modifier.padding(start = if (option.icon != null) 16.dp else 0.dp),
                    text = option.titleText,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(RC.dimen.scd_normal_150))
                    .padding(bottom = dimensionResource(RC.dimen.scd_normal_150))
            ) {
                Text(
                    modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_normal_100)),
                    text = option.details!!.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier.padding(top = dimensionResource(RC.dimen.scd_small_100)),
                    text = option.details.body,
                    style = MaterialTheme.typography.bodyMedium
                )
                option.details.postView?.invoke(option.selected)
            }
        }
    }
}