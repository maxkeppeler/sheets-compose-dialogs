package com.maxkeppeker.sheets.core.views

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.maxkeppeker.sheets.core.models.ImageSource

@Composable
fun IconComponent(
    modifier: Modifier,
    imageSource: ImageSource,
    tint: Color? = null,
) {

    val actualTint = tint ?: imageSource.tint ?: LocalContentColor.current

    val resolvedPainterDrawableRes = imageSource.drawableRes?.let { painterResource(id = it) }
    (imageSource.painter ?: resolvedPainterDrawableRes)?.let {
        Icon(
            modifier = modifier,
            painter = it,
            contentDescription = imageSource.contentDescription,
            tint = actualTint
        )
    }

    imageSource.bitmap?.let {
        Icon(
            modifier = modifier,
            bitmap = it,
            contentDescription = imageSource.contentDescription,
            tint = actualTint
        )
    }

    imageSource.imageVector?.let {
        Icon(
            modifier = modifier,
            imageVector = it,
            contentDescription = imageSource.contentDescription,
            tint = actualTint
        )
    }

}