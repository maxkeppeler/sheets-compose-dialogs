package com.maxkeppeker.sheets.core.icons.outlined

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val EmojiFoodBeverage: ImageVector
    get() {
        if (_emojiFoodBeverage != null) {
            return _emojiFoodBeverage!!
        }
        _emojiFoodBeverage = materialIcon(name = "Outlined.EmojiFoodBeverage") {
            materialPath {
                moveTo(2.0f, 19.0f)
                horizontalLineToRelative(18.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(-18.0f)
                close()
            }
            materialPath {
                moveTo(20.0f, 3.0f)
                horizontalLineTo(4.0f)
                verticalLineToRelative(10.0f)
                curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f)
                horizontalLineToRelative(6.0f)
                curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f)
                verticalLineToRelative(-3.0f)
                horizontalLineToRelative(2.0f)
                curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f)
                verticalLineTo(5.0f)
                curveTo(22.0f, 3.89f, 21.11f, 3.0f, 20.0f, 3.0f)
                close()
                moveTo(16.0f, 13.0f)
                curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f)
                horizontalLineTo(8.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f)
                verticalLineTo(5.0f)
                horizontalLineToRelative(3.0f)
                verticalLineToRelative(1.4f)
                lineTo(7.19f, 7.85f)
                curveTo(7.07f, 7.94f, 7.0f, 8.09f, 7.0f, 8.24f)
                verticalLineToRelative(4.26f)
                curveTo(7.0f, 12.78f, 7.22f, 13.0f, 7.5f, 13.0f)
                horizontalLineToRelative(4.0f)
                curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f)
                verticalLineTo(8.24f)
                curveToRelative(0.0f, -0.15f, -0.07f, -0.3f, -0.19f, -0.39f)
                lineTo(10.0f, 6.4f)
                verticalLineTo(5.0f)
                horizontalLineToRelative(6.0f)
                verticalLineTo(13.0f)
                close()
                moveTo(9.5f, 7.28f)
                lineToRelative(1.5f, 1.2f)
                verticalLineTo(12.0f)
                horizontalLineTo(8.0f)
                verticalLineTo(8.48f)
                lineTo(9.5f, 7.28f)
                close()
                moveTo(20.0f, 8.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineTo(5.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(8.0f)
                close()
            }
        }
        return _emojiFoodBeverage!!
    }

private var _emojiFoodBeverage: ImageVector? = null
