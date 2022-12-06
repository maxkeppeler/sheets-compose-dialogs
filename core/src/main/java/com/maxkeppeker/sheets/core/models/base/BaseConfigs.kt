/*
 *  Copyright (C) 2022. Maximilian Keppeler (https://www.maxkeppeler.com)
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
package com.maxkeppeker.sheets.core.models.base

import com.maxkeppeker.sheets.core.icons.LibIcons
import com.maxkeppeker.sheets.core.utils.BaseConstants.DEFAULT_ICON_STYLE

/**
 * Base configs for dialog-specific configs.
 * @param icons The style of icons that are used for dialog/ view-specific icons.
 */
abstract class BaseConfigs(
    open val icons: LibIcons = DEFAULT_ICON_STYLE
)