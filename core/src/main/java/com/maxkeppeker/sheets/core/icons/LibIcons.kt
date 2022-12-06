@file:Suppress("PropertyName")

package com.maxkeppeker.sheets.core.icons

import androidx.compose.ui.graphics.vector.ImageVector

sealed class LibIcons {

    abstract var EmojiEmotions: ImageVector
    abstract var EmojiNature: ImageVector
    abstract var EmojiFoodBeverage: ImageVector
    abstract var EmojiTransportation: ImageVector
    abstract var EmojiEvents: ImageVector
    abstract var EmojiObjects: ImageVector
    abstract var EmojiSymbols: ImageVector
    abstract var EmojiFlags: ImageVector
    abstract var ContentCopy: ImageVector
    abstract var ContentPaste: ImageVector
    abstract var Apps: ImageVector
    abstract var Tune: ImageVector
    abstract var NotInterested: ImageVector
    abstract var Backspace: ImageVector
    abstract var Clear: ImageVector
    abstract var ChevronRight: ImageVector
    abstract var ChevronLeft: ImageVector
    abstract var ExpandMore: ImageVector
    abstract var Check: ImageVector
    abstract var Star: ImageVector
    abstract var Info: ImageVector
    abstract var Error: ImageVector

    /**
     * [Filled icons](https://material.io/resources/icons/?style=baseline) (previously the only
     * available theme, also known as the baseline theme) are the default icon theme.
     */
    object Filled : LibIcons() {
        override var EmojiEmotions = com.maxkeppeker.sheets.core.icons.filled.EmojiEmotions
        override var EmojiNature = com.maxkeppeker.sheets.core.icons.filled.EmojiNature
        override var EmojiFoodBeverage = com.maxkeppeker.sheets.core.icons.filled.EmojiFoodBeverage
        override var EmojiTransportation = com.maxkeppeker.sheets.core.icons.filled.EmojiTransportation
        override var EmojiEvents = com.maxkeppeker.sheets.core.icons.filled.EmojiEvents
        override var EmojiObjects = com.maxkeppeker.sheets.core.icons.filled.EmojiObjects
        override var EmojiSymbols = com.maxkeppeker.sheets.core.icons.filled.EmojiSymbols
        override var EmojiFlags = com.maxkeppeker.sheets.core.icons.filled.EmojiFlags
        override var ContentCopy = com.maxkeppeker.sheets.core.icons.filled.ContentCopy
        override var ContentPaste = com.maxkeppeker.sheets.core.icons.filled.ContentPaste
        override var Apps = com.maxkeppeker.sheets.core.icons.filled.Apps
        override var Tune = com.maxkeppeker.sheets.core.icons.filled.Tune
        override var NotInterested = com.maxkeppeker.sheets.core.icons.filled.NotInterested
        override var Backspace = com.maxkeppeker.sheets.core.icons.filled.Backspace
        override var Clear = com.maxkeppeker.sheets.core.icons.filled.Clear
        override var ChevronRight = com.maxkeppeker.sheets.core.icons.filled.ChevronRight
        override var ChevronLeft = com.maxkeppeker.sheets.core.icons.filled.ChevronRight
        override var ExpandMore = com.maxkeppeker.sheets.core.icons.filled.ExpandMore
        override var Check = com.maxkeppeker.sheets.core.icons.filled.Check
        override var Star = com.maxkeppeker.sheets.core.icons.filled.Star
        override var Info = com.maxkeppeker.sheets.core.icons.filled.Info
        override var Error = com.maxkeppeker.sheets.core.icons.filled.Error
    }

    /**
     * [Outlined icons](https://material.io/resources/icons/?style=outline) make use of a thin
     * stroke and empty space inside for a lighter appearance.
     */
    object Outlined : LibIcons() {
        override var EmojiEmotions = com.maxkeppeker.sheets.core.icons.outlined.EmojiEmotions
        override var EmojiNature = com.maxkeppeker.sheets.core.icons.outlined.EmojiNature
        override var EmojiFoodBeverage = com.maxkeppeker.sheets.core.icons.outlined.EmojiFoodBeverage
        override var EmojiTransportation = com.maxkeppeker.sheets.core.icons.outlined.EmojiTransportation
        override var EmojiEvents = com.maxkeppeker.sheets.core.icons.outlined.EmojiEvents
        override var EmojiObjects = com.maxkeppeker.sheets.core.icons.outlined.EmojiObjects
        override var EmojiSymbols = com.maxkeppeker.sheets.core.icons.outlined.EmojiSymbols
        override var EmojiFlags = com.maxkeppeker.sheets.core.icons.outlined.EmojiFlags
        override var ContentCopy = com.maxkeppeker.sheets.core.icons.outlined.ContentCopy
        override var ContentPaste = com.maxkeppeker.sheets.core.icons.outlined.ContentPaste
        override var Apps = com.maxkeppeker.sheets.core.icons.outlined.Apps
        override var Tune = com.maxkeppeker.sheets.core.icons.outlined.Tune
        override var NotInterested = com.maxkeppeker.sheets.core.icons.outlined.NotInterested
        override var Backspace = com.maxkeppeker.sheets.core.icons.outlined.Backspace
        override var Clear = com.maxkeppeker.sheets.core.icons.outlined.Clear
        override var ChevronRight = com.maxkeppeker.sheets.core.icons.outlined.ChevronRight
        override var ChevronLeft = com.maxkeppeker.sheets.core.icons.outlined.ChevronLeft
        override var ExpandMore = com.maxkeppeker.sheets.core.icons.outlined.ExpandMore
        override var Check = com.maxkeppeker.sheets.core.icons.outlined.Check
        override var Star = com.maxkeppeker.sheets.core.icons.outlined.Star
        override var Info = com.maxkeppeker.sheets.core.icons.outlined.Info
        override var Error = com.maxkeppeker.sheets.core.icons.outlined.Error
    }

    /**
     * [Rounded icons](https://material.io/resources/icons/?style=round) use a corner radius that
     * pairs well with brands that use heavier typography, curved logos, or circular elements to
     * express their style.
     */
    object Rounded : LibIcons() {
        override var EmojiEmotions = com.maxkeppeker.sheets.core.icons.rounded.EmojiEmotions
        override var EmojiNature = com.maxkeppeker.sheets.core.icons.rounded.EmojiNature
        override var EmojiFoodBeverage = com.maxkeppeker.sheets.core.icons.rounded.EmojiFoodBeverage
        override var EmojiTransportation = com.maxkeppeker.sheets.core.icons.rounded.EmojiTransportation
        override var EmojiEvents = com.maxkeppeker.sheets.core.icons.rounded.EmojiEvents
        override var EmojiObjects = com.maxkeppeker.sheets.core.icons.rounded.EmojiObjects
        override var EmojiSymbols = com.maxkeppeker.sheets.core.icons.rounded.EmojiSymbols
        override var EmojiFlags = com.maxkeppeker.sheets.core.icons.rounded.EmojiFlags
        override var ContentCopy = com.maxkeppeker.sheets.core.icons.rounded.ContentCopy
        override var ContentPaste = com.maxkeppeker.sheets.core.icons.rounded.ContentPaste
        override var Apps = com.maxkeppeker.sheets.core.icons.rounded.Apps
        override var Tune = com.maxkeppeker.sheets.core.icons.rounded.Tune
        override var NotInterested = com.maxkeppeker.sheets.core.icons.rounded.NotInterested
        override var Backspace = com.maxkeppeker.sheets.core.icons.rounded.Backspace
        override var Clear = com.maxkeppeker.sheets.core.icons.rounded.Clear
        override var ChevronRight = com.maxkeppeker.sheets.core.icons.rounded.ChevronRight
        override var ChevronLeft = com.maxkeppeker.sheets.core.icons.rounded.ChevronLeft
        override var ExpandMore = com.maxkeppeker.sheets.core.icons.rounded.ExpandMore
        override var Check = com.maxkeppeker.sheets.core.icons.rounded.Check
        override var Star = com.maxkeppeker.sheets.core.icons.rounded.Star
        override var Info = com.maxkeppeker.sheets.core.icons.rounded.Info
        override var Error = com.maxkeppeker.sheets.core.icons.rounded.Error
    }

    /**
     * [Two-Tone icons](https://material.io/resources/icons/?style=twotone) display corners with
     * straight edges, for a crisp style that remains legible even at smaller scales. These
     * rectangular shapes can support brand styles that are not well-reflected by rounded shapes.
     */
    object TwoTone : LibIcons() {
        override var EmojiEmotions = com.maxkeppeker.sheets.core.icons.twotone.EmojiEmotions
        override var EmojiNature = com.maxkeppeker.sheets.core.icons.twotone.EmojiNature
        override var EmojiFoodBeverage = com.maxkeppeker.sheets.core.icons.twotone.EmojiFoodBeverage
        override var EmojiTransportation = com.maxkeppeker.sheets.core.icons.twotone.EmojiTransportation
        override var EmojiEvents = com.maxkeppeker.sheets.core.icons.twotone.EmojiEvents
        override var EmojiObjects = com.maxkeppeker.sheets.core.icons.twotone.EmojiObjects
        override var EmojiSymbols = com.maxkeppeker.sheets.core.icons.twotone.EmojiSymbols
        override var EmojiFlags = com.maxkeppeker.sheets.core.icons.twotone.EmojiFlags
        override var ContentCopy = com.maxkeppeker.sheets.core.icons.twotone.ContentCopy
        override var ContentPaste = com.maxkeppeker.sheets.core.icons.twotone.ContentPaste
        override var Apps = com.maxkeppeker.sheets.core.icons.twotone.Apps
        override var Tune = com.maxkeppeker.sheets.core.icons.twotone.Tune
        override var NotInterested = com.maxkeppeker.sheets.core.icons.twotone.NotInterested
        override var Backspace = com.maxkeppeker.sheets.core.icons.twotone.Backspace
        override var Clear = com.maxkeppeker.sheets.core.icons.twotone.Clear
        override var ChevronRight = com.maxkeppeker.sheets.core.icons.twotone.ChevronRight
        override var ChevronLeft = com.maxkeppeker.sheets.core.icons.twotone.ChevronLeft
        override var ExpandMore = com.maxkeppeker.sheets.core.icons.twotone.ExpandMore
        override var Check = com.maxkeppeker.sheets.core.icons.twotone.Check
        override var Star = com.maxkeppeker.sheets.core.icons.twotone.Star
        override var Info = com.maxkeppeker.sheets.core.icons.twotone.Info
        override var Error = com.maxkeppeker.sheets.core.icons.twotone.Error
    }

    /**
     * [Sharp icons](https://material.io/resources/icons/?style=sharp) display corners with
     * straight edges, for a crisp style that remains legible even at smaller scales. These
     * rectangular shapes can support brand styles that are not well-reflected by rounded shapes.
     */
    object Sharp : LibIcons() {
        override var EmojiEmotions = com.maxkeppeker.sheets.core.icons.sharp.EmojiEmotions
        override var EmojiNature = com.maxkeppeker.sheets.core.icons.sharp.EmojiNature
        override var EmojiFoodBeverage = com.maxkeppeker.sheets.core.icons.sharp.EmojiFoodBeverage
        override var EmojiTransportation = com.maxkeppeker.sheets.core.icons.sharp.EmojiTransportation
        override var EmojiEvents = com.maxkeppeker.sheets.core.icons.sharp.EmojiEvents
        override var EmojiObjects = com.maxkeppeker.sheets.core.icons.sharp.EmojiObjects
        override var EmojiSymbols = com.maxkeppeker.sheets.core.icons.sharp.EmojiSymbols
        override var EmojiFlags = com.maxkeppeker.sheets.core.icons.sharp.EmojiFlags
        override var ContentCopy = com.maxkeppeker.sheets.core.icons.sharp.ContentCopy
        override var ContentPaste = com.maxkeppeker.sheets.core.icons.sharp.ContentPaste
        override var Apps = com.maxkeppeker.sheets.core.icons.sharp.Apps
        override var Tune = com.maxkeppeker.sheets.core.icons.sharp.Tune
        override var NotInterested = com.maxkeppeker.sheets.core.icons.sharp.NotInterested
        override var Backspace = com.maxkeppeker.sheets.core.icons.sharp.Backspace
        override var Clear = com.maxkeppeker.sheets.core.icons.sharp.Clear
        override var ChevronRight = com.maxkeppeker.sheets.core.icons.sharp.ChevronRight
        override var ChevronLeft = com.maxkeppeker.sheets.core.icons.sharp.ChevronLeft
        override var ExpandMore = com.maxkeppeker.sheets.core.icons.sharp.ExpandMore
        override var Check = com.maxkeppeker.sheets.core.icons.sharp.Check
        override var Star = com.maxkeppeker.sheets.core.icons.sharp.Star
        override var Info = com.maxkeppeker.sheets.core.icons.sharp.Info
        override var Error = com.maxkeppeker.sheets.core.icons.sharp.Error
    }
}