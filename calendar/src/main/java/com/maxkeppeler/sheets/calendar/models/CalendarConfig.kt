/*
 *  Copyright (C) 2022-2023. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeler.sheets.calendar.models

import com.maxkeppeker.sheets.core.icons.LibIcons
import com.maxkeppeker.sheets.core.models.base.BaseConfigs
import com.maxkeppeker.sheets.core.utils.BaseConstants.DEFAULT_ICON_STYLE
import com.maxkeppeler.sheets.calendar.utils.Constants
import java.time.LocalDate
import java.util.Locale

/**
 * The general configuration for the calendar dialog.
 * @param locale The locale of the calendar.
 * @param style The style of the calendar.
 * @param cameraDate The date that is initially displayed when the calendar is opened.
 * @param monthSelection Allow the direct selection of a month.
 * @param yearSelection Allow the direct selection of a year.
 * @param boundary The range of dates that are displayed.
 * @param disabledDates A list of dates that will be marked as disabled and can not be selected.
 * @param icons The style of icons that are used for dialog/ view-specific icons.
 */
class CalendarConfig(
    val locale: Locale = Locale.getDefault(),
    val style: CalendarStyle = CalendarStyle.MONTH,
    val cameraDate: LocalDate? = null,
    val monthSelection: Boolean = Constants.DEFAULT_MONTH_SELECTION,
    val yearSelection: Boolean = Constants.DEFAULT_YEAR_SELECTION,
    val boundary: ClosedRange<LocalDate> = Constants.DEFAULT_RANGE,
    val disabledDates: List<LocalDate>? = null,
    override val icons: LibIcons = DEFAULT_ICON_STYLE,
) : BaseConfigs()