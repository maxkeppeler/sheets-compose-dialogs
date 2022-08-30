package com.maxkeppeler.sheets.list.views


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.views.IconComponent
import com.maxkeppeler.sheets.core.R
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection


@Composable
internal fun ListOptionItemComponent(
    selection: ListSelection,
    option: ListOption,
    onClick: (ListOption) -> Unit
) {

    val showCheckBox = selection is ListSelection.Multiple && selection.showCheckBoxes
    val showRadioButtons = selection is ListSelection.Single && selection.showRadioButtons
    val showSelectionView = showCheckBox || showRadioButtons

    val backgroundColor =
        if (!showSelectionView && option.selected) MaterialTheme.colorScheme.secondaryContainer
        else MaterialTheme.colorScheme.surfaceVariant

    val iconColor = if (!showSelectionView && option.selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    val textColor = if (!showSelectionView && option.selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    val containerModifier = Modifier
        .padding(bottom = 4.dp)
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.medium)
        .clickable { onClick(option) }
        .then(if (!showSelectionView && option.selected) Modifier.background(backgroundColor) else Modifier)

    Row(
        modifier = containerModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val selectionModifier = Modifier
        when {
            showCheckBox -> Checkbox(
                modifier = selectionModifier,
                checked = option.selected,
                onCheckedChange = { onClick(option) }
            )
            showRadioButtons -> RadioButton(
                modifier = selectionModifier,
                selected = option.selected,
                onClick = { onClick(option) }
            )
        }

        option.icon?.let {
            IconComponent(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(dimensionResource(R.dimen.size_150)),
                imageSource = it,
                tint = iconColor
            )
        }

        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .padding(start = 16.dp)
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
