package com.maxkeppeker.sheets.core.utils

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

/**
 * Joins multiple test tags together into a sequence.
 * @param testTags The test tags you apply to the element.
 */
fun testSequenceTagOf(vararg testTags: Any): String = testTags.joinToString()