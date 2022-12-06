package com.maxkeppeker.sheets.core.icons.outlined

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Backspace: ImageVector
    get() {
        if (_backspace != null) {
            return _backspace!!
        }
        _backspace = materialIcon(name = "Outlined.Backspace") {
            materialPath {
                moveTo(22.0f, 3.0f)
                lineTo(7.0f, 3.0f)
                curveToRelative(-0.69f, 0.0f, -1.23f, 0.35f, -1.59f, 0.88f)
                lineTo(0.0f, 12.0f)
                lineToRelative(5.41f, 8.11f)
                curveToRelative(0.36f, 0.53f, 0.9f, 0.89f, 1.59f, 0.89f)
                horizontalLineToRelative(15.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                lineTo(24.0f, 5.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                close()
                moveTo(22.0f, 19.0f)
                lineTo(7.07f, 19.0f)
                lineTo(2.4f, 12.0f)
                lineToRelative(4.66f, -7.0f)
                lineTo(22.0f, 5.0f)
                verticalLineToRelative(14.0f)
                close()
                moveTo(10.41f, 17.0f)
                lineTo(14.0f, 13.41f)
                lineTo(17.59f, 17.0f)
                lineTo(19.0f, 15.59f)
                lineTo(15.41f, 12.0f)
                lineTo(19.0f, 8.41f)
                lineTo(17.59f, 7.0f)
                lineTo(14.0f, 10.59f)
                lineTo(10.41f, 7.0f)
                lineTo(9.0f, 8.41f)
                lineTo(12.59f, 12.0f)
                lineTo(9.0f, 15.59f)
                close()
            }
        }
        return _backspace!!
    }

private var _backspace: ImageVector? = null
