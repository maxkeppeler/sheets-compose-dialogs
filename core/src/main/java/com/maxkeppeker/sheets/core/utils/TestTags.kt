/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.maxkeppeker.sheets.core.utils

/**
 * Test tags that used for the compose UI tests.
 */
object TestTags {

    /*
     *  Test tags for CoreView
     */

    const val POPUP_BASE_CONTAINER = "popup_base_container"
    const val POPUP_BASE_CONTENT = "popup_base_content"

    const val DIALOG_BASE_CONTAINER = "dialog_base_container"
    const val DIALOG_BASE_CONTENT = "dialog_base_content"

    const val FRAME_BASE_CONTENT = "frame_base_content"
    const val FRAME_BASE_HEADER = "frame_base_header"
    const val FRAME_BASE_NO_HEADER = "frame_base_no_header"
    const val FRAME_BASE_BUTTONS = "frame_base_buttons"
    const val FRAME_BASE_NO_BUTTONS = "frame_base_no_buttons"

    const val HEADER_DEFAULT = "header_default"
    const val HEADER_DEFAULT_TEXT = "header_default_text"
    const val HEADER_DEFAULT_ICON = "header_default_icon"

    const val BUTTON_EXTRA = "button_extra"
    const val BUTTON_POSITIVE = "button_positive"
    const val BUTTON_NEGATIVE = "button_negative"
    const val BUTTON_ICON = "button_icon"


    /*
     *  Test tags for CalendarView
     */

    const val CALENDAR_NEXT_ACTION = "calendar_next"
    const val CALENDAR_PREVIOUS_ACTION = "calendar_previous"
    const val CALENDAR_MONTH_TITLE = "calendar_month_title"
    const val CALENDAR_YEAR_TITLE = "calendar_year_title"
    const val CALENDAR_DATE = "calendar_date"
    const val CALENDAR_CW = "calendar_cw"
    const val CALENDAR_HEADER_CW = "calendar_header_cw"
    const val CALENDAR_HEADER_DAY = "calendar_header_day"


    /*
     *  Test tags for ColorView
     */

    const val COLOR_TEMPLATE_SELECTION = "color_template"
    const val COLOR_CUSTOM_SELECTION = "color_custom"
    const val COLOR_CUSTOM_VALUE_SLIDER = "color_custom_value_slider"


    /*
     *  Test tags for ClockView
     */

    const val CLOCK_12_HOUR_FORMAT = "12_hour_format"


    /*
     *  Test tags for ClockView & DurationView
     */

    const val KEYBOARD_KEY = "keyboard_key"

    /*
     *  Test tags for EmojiView
     */
    const val EMOJI_SELECTION = "emoji_selection"
    const val EMOJI_CATEGORY = "emoji_category"
    const val EMOJI_CATEGORY_TAB = "emoji_category_tab"

    /*
    *  Test tags for DateTimeView
    */

    const val DATE_TIME_VALUE_SELECTION = "date_time_value_selection"
    const val DATE_TIME_VALUE_CONTAINER_SELECTION = "date_time_value_container_selection"


    /*
     *  Test tags for InputView
     */

    const val LIST_VIEW_SELECTION = "list_view_selection"
    const val OPTION_VIEW_SELECTION = "option_view_selection"


    /*
     *  Test tags for InfoView
     */

    const val INFO_BODY_DEFAULT = "info_body_default"
    const val INFO_BODY_DEFAULT_TEXT = "info_body_default_text"


    /*
     *  Test tags for StateView
     */

    const val STATE_LOADING_CIRCULAR = "state_loading_circular"
    const val STATE_LOADING_LINEAR = "state_loading_linear"
    const val STATE_LOADING_LABEL_PERCENTAGE = "state_loading_label_percentage"
    const val STATE_FAILURE = "state_failure"
    const val STATE_SUCCESS = "state_success"
    const val STATE_VIEW_LABEL_TEXT = "state_view_label_text"

    /*
     *  Test tags for RatingView
     */

    const val RATING_STAR_INPUT = "rating_star_input"
    const val RATING_FEEDBACK_TEXT_FIELD = "rating_feedback_text_field"
    const val RATING_FEEDBACK_TEXT_FIELD_TYPE = "rating_feedback_text_field_type"
    const val RATING_FEEDBACK_TEXT_FIELD_ERROR_TEXT = "rating_feedback_text_field_error_text"
    const val RATING_BODY_DEFAULT = "rating_body_default"
    const val RATING_BODY_DEFAULT_TEXT = "rating_body_default_text"


    /*
     *  Test tags for InputView
     */

    const val INPUT_ITEM_HEADER = "input_header"
    const val INPUT_ITEM_HEADER_TITLE = "input_header_title"
    const val INPUT_ITEM_HEADER_BODY = "input_header_body"
    const val INPUT_ITEM_HEADER_ICON = "input_header_icon"
    const val INPUT_ITEM_OVERLAY = "input_overlay"

    const val INPUT_ITEM_RADIOBUTTON_GROUP = "input_button_group"
    const val INPUT_ITEM_RADIOBUTTON_GROUP_ITEM = "input_button_group_item"
    const val INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_RADIOBUTTON = "input_button_group_item_radio"
    const val INPUT_ITEM_RADIOBUTTON_GROUP_ITEM_TEXT = "input_button_group_item_text"

    const val INPUT_ITEM_CHECKBOX_GROUP = "input_checkbox_group"
    const val INPUT_ITEM_CHECKBOX_GROUP_ITEM = "input_checkbox_group_item"
    const val INPUT_ITEM_CHECKBOX_GROUP_ITEM_CHECKBOX = "input_checkbox_group_item_checkbox"
    const val INPUT_ITEM_CHECKBOX_GROUP_ITEM_TEXT = "input_checkbox_group_item_text"

    const val INPUT_ITEM_CHECKBOX = "input_checkbox"
    const val INPUT_ITEM_CHECKBOX_CHECKBOX = "input_checkbox_checkbox"
    const val INPUT_ITEM_CHECKBOX_TEXT = "input_checkbox_text"

    const val INPUT_ITEM_TEXT_FIELD = "input_text_field"
    const val INPUT_ITEM_TEXT_FIELD_TYPE = "input_text_field_type"
    const val INPUT_ITEM_TEXT_FIELD_ERROR_TEXT = "input_text_field_error_text"

    const val INPUT_ITEM_TEXT = "input_text"
    const val INPUT_ITEM_TEXT_TEXT = "input_text_text"

    const val INPUT_ITEM_DIVIDER = "input_divider"
}