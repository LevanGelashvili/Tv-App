package com.balevanciaga.tvapp.presentation.list.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.balevanciaga.tvapp.main.ui.theme.Theme

@Composable
fun SearchBar(
    onFilter: (query: String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = query,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Theme.colors.onBackground,
            unfocusedBorderColor = Theme.colors.primary,
            focusedBorderColor = Theme.colors.secondary,
            cursorColor = Theme.colors.secondary
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
            query = it
            onFilter(it)
        }
    )
}