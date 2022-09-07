package com.maxkeppeker.sheets.core.views

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.maxkeppeker.sheets.core.models.base.IconSource

/**
 * Icon component that is displayed in various places in a dialog.
 * @param modifier The modifier that is applied to this icon.
 * @param iconSource The icon that is used.
 * @param tint The color that is used to tint the icon.
 */
@Composable
fun IconComponent(
    modifier: Modifier,
    iconSource: IconSource,
    tint: Color? = null,
) {

    val actualTint = tint ?: iconSource.tint ?: LocalContentColor.current

    val resolvedPainterDrawableRes = iconSource.drawableRes?.let { painterResource(id = it) }
    (iconSource.painter ?: resolvedPainterDrawableRes)?.let {
        Icon(
            modifier = modifier,
            painter = it,
            contentDescription = iconSource.contentDescription,
            tint = actualTint
        )
    }

    iconSource.bitmap?.let {
        Icon(
            modifier = modifier,
            bitmap = it,
            contentDescription = iconSource.contentDescription,
            tint = actualTint
        )
    }

    iconSource.imageVector?.let {
        Icon(
            modifier = modifier,
            imageVector = it,
            contentDescription = iconSource.contentDescription,
            tint = actualTint
        )
    }

}