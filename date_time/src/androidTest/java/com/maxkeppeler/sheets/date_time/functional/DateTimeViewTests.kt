@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.date_time.functional

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

//@RunWith(AndroidJUnit4::class)
class DateTimeViewTests {

    @get:Rule
    val rule = createComposeRule()

//    @Test
//    fun dateTimeViewTimeLocaleGermanySelectionSuccess() {
//        val testTime = LocalTime.of(10, 30)
//        var selectedTime: LocalTime? = null
//        rule.setContentAndWaitForIdle {
//            DateTimeView(
//                sheetState = SheetState(visible = true),
//                selection = DateTimeSelection.Time(
//                    locale = Locale.GERMANY,
//                    onPositiveClick = { selectedTime = it }
//                ),
//                config = DateTimeConfig()
//            )
//        }
//        rule.onNodeWithTags(TestTags.DATE_TIME_VALUE_SELECTION, "Hour").performClick()
//        rule.waitForIdle()
////        composeTestRule.onNodeWithTags(TestTags.DATE_TIME_VALUE_CONTAINER_SELECTION, "Hour").performTouchInput { swipeUp() }
////        composeTestRule.onNodeWithTags(TestTags.DATE_TIME_VALUE_SELECTION, "Hour", 10).performClick()
////        composeTestRule.waitForIdle()
////        composeTestRule.onNodeWithTags(TestTags.DATE_TIME_VALUE_SELECTION.plus("_${UnitSelection.Minute::class.simpleName}")).performClick()
////        composeTestRule.waitForIdle()
////        composeTestRule.onNodeWithTags(TestTags.DATE_TIME_VALUE_SELECTION.plus("_${UnitSelection.Minute::class.simpleName}"), 30).performClick()
////        composeTestRule.waitForIdle()
////        composeTestRule.onPositiveButton().performClick()
//        assert(selectedTime == testTime)
//    }
}