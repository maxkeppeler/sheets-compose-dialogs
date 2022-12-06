package com.maxkeppeker.sheets.core.icons.rounded

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val EmojiFlags: ImageVector
    get() {
        if (_emojiFlags != null) {
            return _emojiFlags!!
        }
        _emojiFlags = materialIcon(name = "Rounded.EmojiFlags") {
            materialPath {
                moveTo(19.0f, 9.0f)
                horizontalLineToRelative(-5.0f)
                lineToRelative(-0.72f, -1.45f)
                curveTo(13.11f, 7.21f, 12.76f, 7.0f, 12.38f, 7.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(5.72f)
                curveTo(7.6f, 5.38f, 8.0f, 4.74f, 8.0f, 4.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                reflectiveCurveTo(4.0f, 2.9f, 4.0f, 4.0f)
                curveToRelative(0.0f, 0.74f, 0.4f, 1.38f, 1.0f, 1.72f)
                verticalLineTo(20.0f)
                curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f)
                reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f)
                verticalLineToRelative(-3.0f)
                horizontalLineToRelative(5.0f)
                lineToRelative(0.72f, 1.45f)
                curveToRelative(0.17f, 0.34f, 0.52f, 0.55f, 0.89f, 0.55f)
                horizontalLineTo(19.0f)
                curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f)
                verticalLineToRelative(-8.0f)
                curveTo(20.0f, 9.45f, 19.55f, 9.0f, 19.0f, 9.0f)
                close()
                moveTo(18.0f, 17.0f)
                horizontalLineToRelative(-4.0f)
                lineToRelative(-1.0f, -2.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(9.0f)
                horizontalLineToRelative(5.0f)
                lineToRelative(1.0f, 2.0f)
                horizontalLineToRelative(5.0f)
                verticalLineTo(17.0f)
                close()
            }
        }
        return _emojiFlags!!
    }

private var _emojiFlags: ImageVector? = null
