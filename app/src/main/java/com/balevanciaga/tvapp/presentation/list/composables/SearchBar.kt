package com.balevanciaga.tvapp.presentation.list.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.balevanciaga.tvapp.main.ui.theme.Theme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onFilter: (query: String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = query,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Theme.colors.onBackground,
            backgroundColor = Theme.colors.backgroundVariant,
            cursorColor = Theme.colors.primary,
            focusedBorderColor = Theme.colors.primary,
            unfocusedBorderColor = Theme.colors.backgroundVariant
        ),
        trailingIcon = {
            Icon(
                tint = Theme.colors.primary,
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        maxLines = 1,
        onValueChange = {
            onFilter(it)
        }
    )
}