package com.maxkeppeker.sheets.core.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

class ImageSource {

    @DrawableRes
    var drawableRes: Int? = null
    var imageVector: ImageVector? = null
    var bitmap: ImageBitmap? = null
    var painter: Painter? = null
    var contentDescription: String? = null
    var tint: Color? = null

    constructor(
        @DrawableRes drawableRes: Int,
        contentDescription: String? = null,
        tint: Color? = null
    ) {
        this.drawableRes = drawableRes
        this.contentDescription = contentDescription
        this.tint = tint
    }

    constructor(
        imageVector: ImageVector,
        contentDescription: String? = null,
        tint: Color? = null
    ) {
        this.imageVector = imageVector
        this.contentDescription = contentDescription
        this.tint = tint
    }

    constructor(
        bitmap: ImageBitmap,
        contentDescription: String? = null,
        tint: Color? = null
    ) {
        this.bitmap = bitmap
        this.contentDescription = contentDescription
        this.tint = tint
    }

    constructor(
        painter: Painter,
        contentDescription: String? = null,
        tint: Color? = null
    ) {
        this.painter = painter
        this.contentDescription = contentDescription
        this.tint = tint
    }
}