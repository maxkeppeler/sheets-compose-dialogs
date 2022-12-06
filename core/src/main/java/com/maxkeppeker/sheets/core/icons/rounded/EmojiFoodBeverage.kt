package com.maxkeppeker.sheets.core.icons.rounded

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val EmojiFoodBeverage: ImageVector
    get() {
        if (_emojiFoodBeverage != null) {
            return _emojiFoodBeverage!!
        }
        _emojiFoodBeverage = materialIcon(name = "Rounded.EmojiFoodBeverage") {
            materialPath {
                moveTo(19.0f, 19.0f)
                horizontalLineTo(3.0f)
                curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f)
                reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f)
                horizontalLineToRelative(16.0f)
                curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f)
                reflectiveCurveTo(19.55f, 19.0f, 19.0f, 19.0f)
                close()
            }
            materialPath {
                moveTo(20.0f, 3.0f)
                horizontalLineTo(9.0f)
                verticalLineToRelative(2.4f)
                lineToRelative(1.81f, 1.45f)
                curveTo(10.93f, 6.94f, 11.0f, 7.09f, 11.0f, 7.24f)
                verticalLineToRelative(4.26f)
                curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f)
                horizontalLineToRelative(-4.0f)
                curveTo(6.22f, 12.0f, 6.0f, 11.78f, 6.0f, 11.5f)
                verticalLineTo(7.24f)
                curveToRelative(0.0f, -0.15f, 0.07f, -0.3f, 0.19f, -0.39f)
                lineTo(8.0f, 5.4f)
                verticalLineTo(3.0f)
                horizontalLineTo(6.0f)
                curveTo(4.9f, 3.0f, 4.0f, 3.9f, 4.0f, 5.0f)
                verticalLineToRelative(8.0f)
                curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f)
                horizontalLineToRelative(6.0f)
                curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f)
                verticalLineToRelative(-3.0f)
                horizontalLineToRelative(2.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineTo(5.0f)
                curveTo(22.0f, 3.9f, 21.1f, 3.0f, 20.0f, 3.0f)
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
