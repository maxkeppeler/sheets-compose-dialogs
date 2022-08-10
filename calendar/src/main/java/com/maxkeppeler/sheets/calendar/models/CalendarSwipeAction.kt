package com.maxkeppeler.sheets.calendar.models

/**
 * Defined swipe actions when calendar is [CalendarDisplayMode.CALENDAR]-mode.
 */
internal enum class CalendarSwipeAction {

    /**
     * Previous (month or week) action.
     */
    PREV,

    /**
     * Next (month or week) action.
     */
    NEXT,

    /**
     * No action. Used to reset from recognized swipe action.
     */
    NONE
}