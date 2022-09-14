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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun UseCasesDemo(onSelectUseCase: (UseCase) -> Unit) {

    val groupedByCategories = remember {
        val value = UseCase.values().groupBy { it.category }
        mutableStateOf(value)
    }

    LazyVerticalGrid(
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        groupedByCategories.value.forEach { category ->
            item(span = { GridItemSpan(2) }) { UseCaseCategoryItem(category) }
            itemsIndexed(category.value) { i, useCase ->
                UseCaseItem(i, useCase, onSelectUseCase)
            }
        }
    }
}

@Composable
internal fun UseCaseCategoryItem(setup: Map.Entry<UseCaseCategory, List<UseCase>>) {
    Text(
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        text = setup.key.title,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
internal fun UseCaseItem(index: Int, useCase: UseCase, onSelectUseCase: (UseCase) -> Unit) {
    ElevatedCard(
        onClick = { onSelectUseCase(useCase) }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "${index.plus(1)}. Sample",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(12.dp))
            useCase.specifics.forEach { info ->
                Row(
                    modifier = Modifier.padding(bottom = if (index < UseCase.values().size) 4.dp else 0.dp),
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .size(8.dp),
                        imageVector = Icons.Filled.Circle,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = info,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}