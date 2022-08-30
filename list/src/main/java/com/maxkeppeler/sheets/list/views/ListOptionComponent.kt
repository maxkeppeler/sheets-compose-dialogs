package com.maxkeppeler.sheets.list.views


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.list.models.ListConfig
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection

@Composable
fun ListOptionComponent(
    modifier: Modifier,
    selection: ListSelection,
    options: List<ListOption>,
    onOptionChange: (Int, ListOption) -> Unit,
) {

    val lazyContainerModifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp)

    val onClick: (ListOption) -> Unit = { option ->
        val index = options.indexOf(option)
        val newOption = option.copy(selected = !option.selected)
        onOptionChange(index, newOption)
    }

    LazyColumn(
        modifier = lazyContainerModifier,
    ) {
        items(options) { option ->
            ListOptionItemComponent(
                selection = selection,
                option = option,
                onClick = onClick,
            )
        }
    }
}