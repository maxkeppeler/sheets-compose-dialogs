package com.maxkeppeker.sheets.core.icons.sharp

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val ContentCopy: ImageVector
    get() {
        if (_contentCopy != null) {
            return _contentCopy!!
        }
        _contentCopy = materialIcon(name = "Sharp.ContentCopy") {
            materialPath {
                moveTo(16.0f, 1.0f)
                lineTo(2.0f, 1.0f)
                verticalLineToRelative(16.0f)
                horizontalLineToRelative(2.0f)
                lineTo(4.0f, 3.0f)
                horizontalLineToRelative(12.0f)
                lineTo(16.0f, 1.0f)
                close()
                moveTo(21.0f, 5.0f)
                lineTo(6.0f, 5.0f)
                verticalLineToRelative(18.0f)
                horizontalLineToRelative(15.0f)
                lineTo(21.0f, 5.0f)
                close()
                moveTo(19.0f, 21.0f)
                lineTo(8.0f, 21.0f)
                lineTo(8.0f, 7.0f)
                horizontalLineToRelative(11.0f)
                verticalLineToRelative(14.0f)
                close()
            }
        }
        return _contentCopy!!
    }

private var _contentCopy: ImageVector? = null
