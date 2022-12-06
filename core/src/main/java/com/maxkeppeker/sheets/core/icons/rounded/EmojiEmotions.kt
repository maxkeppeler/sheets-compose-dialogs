package com.maxkeppeker.sheets.core.icons.rounded

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val EmojiEmotions: ImageVector
    get() {
        if (_roundedEmojiEmotions != null) {
            return _roundedEmojiEmotions!!
        }
        _roundedEmojiEmotions = materialIcon(name = "Rounded.EmojiEmotions") {
            materialPath {
                moveTo(11.99f, 2.0f)
                curveTo(6.47f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f)
                curveToRelative(0.0f, 5.52f, 4.47f, 10.0f, 9.99f, 10.0f)
                curveTo(17.52f, 22.0f, 22.0f, 17.52f, 22.0f, 12.0f)
                curveTo(22.0f, 6.48f, 17.52f, 2.0f, 11.99f, 2.0f)
                close()
                moveTo(8.5f, 8.0f)
                curveTo(9.33f, 8.0f, 10.0f, 8.67f, 10.0f, 9.5f)
                reflectiveCurveTo(9.33f, 11.0f, 8.5f, 11.0f)
                reflectiveCurveTo(7.0f, 10.33f, 7.0f, 9.5f)
                reflectiveCurveTo(7.67f, 8.0f, 8.5f, 8.0f)
                close()
                moveTo(16.71f, 14.72f)
                curveTo(15.8f, 16.67f, 14.04f, 18.0f, 12.0f, 18.0f)
                reflectiveCurveToRelative(-3.8f, -1.33f, -4.71f, -3.28f)
                curveTo(7.13f, 14.39f, 7.37f, 14.0f, 7.74f, 14.0f)
                horizontalLineToRelative(8.52f)
                curveTo(16.63f, 14.0f, 16.87f, 14.39f, 16.71f, 14.72f)
                close()
                moveTo(15.5f, 11.0f)
                curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f)
                reflectiveCurveTo(14.67f, 8.0f, 15.5f, 8.0f)
                reflectiveCurveTo(17.0f, 8.67f, 17.0f, 9.5f)
                reflectiveCurveTo(16.33f, 11.0f, 15.5f, 11.0f)
                close()
            }
        }
        return _roundedEmojiEmotions!!
    }

private var _roundedEmojiEmotions: ImageVector? = null
