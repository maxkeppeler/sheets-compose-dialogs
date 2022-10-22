package com.maxkeppeker.sheets.core.utils

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

/**
 * Applies multiple-tags-sequence to allow a modified element to be found in tests.
 * @param testTags The test tags you apply to the element.
 */
@Stable
fun Modifier.testTags(vararg testTags: Any) = semantics(
    properties = {
        testTag = testSequenceTagOf(*testTags)
    }
)