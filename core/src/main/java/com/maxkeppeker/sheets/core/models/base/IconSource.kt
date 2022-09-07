@file:Suppress("unused")

package com.maxkeppeker.sheets.core.models.base

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * An icon from various sources alongside an optional contentDescription and tint.
 */
class IconSource {

    @DrawableRes
    var drawableRes: Int? = null
    var imageVector: ImageVector? = null
    var bitmap: ImageBitmap? = null
    var painter: Painter? = null
    var contentDescription: String? = null
    var tint: Color? = null

    /**
     * Create an icon from a drawable res.
     * @param drawableRes Res of drawable to use as icon
     * @param contentDescription Text that is used as content description for the icon
     * @param tint Color that is used to tint the icon
     */
    constructor(
        @DrawableRes drawableRes: Int,
        contentDescription: String? = null,
        tint: Color? = null
    ) {
        this.drawableRes = drawableRes
        this.contentDescription = contentDescription
        this.tint = tint
    }

    /**
     * Create an icon from a drawable res.
     * @param imageVector The vector graphic object to use as icon
     * @param contentDescription Text that is used as content description for the icon
     * @param tint Color that is used to tint the icon
     */
    constructor(
        imageVector: ImageVector,
        contentDescription: String? = null,
        tint: Color? = null
    ) {
        this.imageVector = imageVector
        this.contentDescription = contentDescription
        this.tint = tint
    }

    /**
     * Create an icon from a drawable res.
     * @param bitmap Drawable bitmap to use as icon
     * @param contentDescription Text that is used as content description for the icon
     * @param tint Color that is used to tint the icon
     */
    constructor(
        bitmap: ImageBitmap,
        contentDescription: String? = null,
        tint: Color? = null
    ) {
        this.bitmap = bitmap
        this.contentDescription = contentDescription
        this.tint = tint
    }

    /**
     * Create an icon from a drawable res.
     * @param painter Painter drawable to use as icon
     * @param contentDescription Text that is used as content description for the icon
     * @param tint Color that is used to tint the icon
     */
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