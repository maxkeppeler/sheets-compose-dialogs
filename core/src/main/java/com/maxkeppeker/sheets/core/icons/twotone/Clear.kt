package com.maxkeppeker.sheets.core.icons.twotone

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Clear: ImageVector
    get() {
        if (_clear != null) {
            return _clear!!
        }
        _clear = materialIcon(name = "TwoTone.Clear") {
            materialPath {
                moveTo(19.0f, 6.41f)
                lineTo(17.59f, 5.0f)
                lineTo(12.0f, 10.59f)
                lineTo(6.41f, 5.0f)
                lineTo(5.0f, 6.41f)
                lineTo(10.59f, 12.0f)
                lineTo(5.0f, 17.59f)
                lineTo(6.41f, 19.0f)
                lineTo(12.0f, 13.41f)
                lineTo(17.59f, 19.0f)
                lineTo(19.0f, 17.59f)
                lineTo(13.41f, 12.0f)
                lineTo(19.0f, 6.41f)
                close()
            }
        }
        return _clear!!
    }

private var _clear: ImageVector? = null
