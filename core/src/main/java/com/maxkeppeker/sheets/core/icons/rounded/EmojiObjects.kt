package com.maxkeppeker.sheets.core.icons.rounded

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val EmojiObjects: ImageVector
    get() {
        if (_emojiObjects != null) {
            return _emojiObjects!!
        }
        _emojiObjects = materialIcon(name = "Rounded.EmojiObjects") {
            materialPath {
                moveTo(12.0f, 3.0f)
                curveToRelative(-0.46f, 0.0f, -0.93f, 0.04f, -1.4f, 0.14f)
                curveTo(7.84f, 3.67f, 5.64f, 5.9f, 5.12f, 8.66f)
                curveToRelative(-0.48f, 2.61f, 0.48f, 5.01f, 2.22f, 6.56f)
                curveTo(7.77f, 15.6f, 8.0f, 16.13f, 8.0f, 16.69f)
                verticalLineTo(19.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(0.28f)
                curveToRelative(0.35f, 0.6f, 0.98f, 1.0f, 1.72f, 1.0f)
                reflectiveCurveToRelative(1.38f, -0.4f, 1.72f, -1.0f)
                horizontalLineTo(14.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineToRelative(-2.31f)
                curveToRelative(0.0f, -0.55f, 0.22f, -1.09f, 0.64f, -1.46f)
                curveTo(18.09f, 13.95f, 19.0f, 12.08f, 19.0f, 10.0f)
                curveTo(19.0f, 6.13f, 15.87f, 3.0f, 12.0f, 3.0f)
                close()
                moveTo(12.5f, 14.0f)
                horizontalLineToRelative(-1.0f)
                verticalLineToRelative(-2.59f)
                lineTo(9.67f, 9.59f)
                lineToRelative(0.71f, -0.71f)
                lineTo(12.0f, 10.5f)
                lineToRelative(1.62f, -1.62f)
                lineToRelative(0.71f, 0.71f)
                lineToRelative(-1.83f, 1.83f)
                verticalLineTo(14.0f)
                close()
                moveTo(13.5f, 19.0f)
                curveToRelative(-0.01f, 0.0f, -0.02f, -0.01f, -0.03f, -0.01f)
                verticalLineTo(19.0f)
                horizontalLineToRelative(-2.94f)
                verticalLineToRelative(-0.01f)
                curveToRelative(-0.01f, 0.0f, -0.02f, 0.01f, -0.03f, 0.01f)
                curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f)
                curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f)
                curveToRelative(0.01f, 0.0f, 0.02f, 0.01f, 0.03f, 0.01f)
                verticalLineTo(18.0f)
                horizontalLineToRelative(2.94f)
                verticalLineToRelative(0.01f)
                curveToRelative(0.01f, 0.0f, 0.02f, -0.01f, 0.03f, -0.01f)
                curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f)
                curveTo(14.0f, 18.78f, 13.78f, 19.0f, 13.5f, 19.0f)
                close()
                moveTo(13.5f, 17.0f)
                horizontalLineToRelative(-3.0f)
                curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f)
                curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f)
                horizontalLineToRelative(3.0f)
                curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f)
                curveTo(14.0f, 16.78f, 13.78f, 17.0f, 13.5f, 17.0f)
                close()
            }
        }
        return _emojiObjects!!
    }

private var _emojiObjects: ImageVector? = null
