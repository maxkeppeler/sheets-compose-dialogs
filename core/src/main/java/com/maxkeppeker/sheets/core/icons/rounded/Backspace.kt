package com.maxkeppeker.sheets.core.icons.rounded

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Backspace: ImageVector
    get() {
        if (_backspace != null) {
            return _backspace!!
        }
        _backspace = materialIcon(name = "Rounded.Backspace") {
            materialPath {
                moveTo(22.0f, 3.0f)
                lineTo(7.0f, 3.0f)
                curveToRelative(-0.69f, 0.0f, -1.23f, 0.35f, -1.59f, 0.88f)
                lineTo(0.37f, 11.45f)
                curveToRelative(-0.22f, 0.34f, -0.22f, 0.77f, 0.0f, 1.11f)
                lineToRelative(5.04f, 7.56f)
                curveToRelative(0.36f, 0.52f, 0.9f, 0.88f, 1.59f, 0.88f)
                horizontalLineToRelative(15.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                lineTo(24.0f, 5.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                close()
                moveTo(18.3f, 16.3f)
                curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f)
                lineTo(14.0f, 13.41f)
                lineToRelative(-2.89f, 2.89f)
                curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f)
                curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f)
                lineTo(12.59f, 12.0f)
                lineTo(9.7f, 9.11f)
                curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f)
                curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f)
                lineTo(14.0f, 10.59f)
                lineToRelative(2.89f, -2.89f)
                curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f)
                curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f)
                lineTo(15.41f, 12.0f)
                lineToRelative(2.89f, 2.89f)
                curveToRelative(0.38f, 0.38f, 0.38f, 1.02f, 0.0f, 1.41f)
                close()
            }
        }
        return _backspace!!
    }

private var _backspace: ImageVector? = null
