package com.maxkeppeker.sheets.core.icons.sharp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val EmojiFlags: ImageVector
    get() {
        if (_emojiFlags != null) {
            return _emojiFlags!!
        }
        _emojiFlags = materialIcon(name = "Sharp.EmojiFlags") {
            materialPath {
                moveTo(14.0f, 9.0f)
                lineToRelative(-1.0f, -2.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(5.72f)
                curveTo(7.6f, 5.38f, 8.0f, 4.74f, 8.0f, 4.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                reflectiveCurveTo(4.0f, 2.9f, 4.0f, 4.0f)
                curveToRelative(0.0f, 0.74f, 0.4f, 1.38f, 1.0f, 1.72f)
                verticalLineTo(21.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(-4.0f)
                horizontalLineToRelative(5.0f)
                lineToRelative(1.0f, 2.0f)
                horizontalLineToRelative(7.0f)
                verticalLineTo(9.0f)
                horizontalLineTo(14.0f)
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
