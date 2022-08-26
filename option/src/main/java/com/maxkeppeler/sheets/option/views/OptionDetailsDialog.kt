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
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .then(
                        if (option.disabled || option.selected) Modifier.background(
                            backgroundColor
                        ) else Modifier
                    )
                    .padding(16.dp), verticalAlignment = Alignment.CenterVertically
            ) {

                option.icon?.let {
                    IconComponent(
                        modifier = Modifier.size(dimensionResource(RC.dimen.size_150)),
                        imageSource = it,
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
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = option.details!!.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = option.details.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}