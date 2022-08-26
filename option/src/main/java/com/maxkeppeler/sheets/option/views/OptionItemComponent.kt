package com.maxkeppeler.sheets.option.views


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.option.models.Option

@Composable
internal fun OptionItemComponent(
    option: Option,
    onClick: (Option) -> Unit,
    grid: Boolean = true,
    size: MutableState<Size?>? = null,
) {
    val backgroundColor = if (option.selected) MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.surfaceVariant

    val iconColor = if (option.selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    val textColor = if (option.selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    val containerModifier = Modifier
        .padding(4.dp)
        .wrapContentHeight()
        .clip(MaterialTheme.shapes.medium)
        .clickable(!option.disabled) { onClick(option) }
        .then(if (option.disabled || option.selected) Modifier.background(backgroundColor) else Modifier)

    val showInfoDialog = remember { mutableStateOf(false) }
    option.details?.let {
        OptionDetailsDialog(
            show = showInfoDialog,
            option = option,
            backgroundColor = backgroundColor,
            iconColor = iconColor,
            textColor = textColor,
        )
    }

    val onInfoClick = { showInfoDialog.value = !showInfoDialog.value }
    if (grid) OptionGridItemComponent(
        option = option,
        modifier = containerModifier,
        iconColor = iconColor,
        textColor = textColor,
        size = size,
        onInfoClick = onInfoClick
    ) else OptionListItemComponent(
        option = option,
        modifier = containerModifier,
        iconColor = iconColor,
        textColor = textColor,
        onInfoClick = onInfoClick
    )
}