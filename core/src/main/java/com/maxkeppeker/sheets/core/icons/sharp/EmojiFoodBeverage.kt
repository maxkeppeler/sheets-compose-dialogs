package com.maxkeppeker.sheets.core.icons.sharp

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val EmojiFoodBeverage: ImageVector
    get() {
        if (_emojiFoodBeverage != null) {
            return _emojiFoodBeverage!!
        }
        _emojiFoodBeverage = materialIcon(name = "Sharp.EmojiFoodBeverage") {
            materialPath {
                moveTo(2.0f, 19.0f)
                horizontalLineToRelative(18.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(-18.0f)
                close()
            }
            materialPath {
                moveTo(20.0f, 3.0f)
                horizontalLineTo(9.0f)
                verticalLineToRelative(2.4f)
                lineTo(11.0f, 7.0f)
                verticalLineToRelative(5.0f)
                horizontalLineTo(6.0f)
                verticalLineTo(7.0f)
                lineToRelative(2.0f, -1.6f)
                verticalLineTo(3.0f)
                horizontalLineTo(4.0f)
                verticalLineToRelative(14.0f)
                horizontalLineToRelative(14.0f)
                verticalLineToRelative(-7.0f)
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
