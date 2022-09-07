package com.balevanciaga.tvapp.presentation.list.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.balevanciaga.tvapp.main.ui.theme.Theme
import com.balevanciaga.tvapp.main.ui.theme.spaceGray

@Composable
fun SearchBar(
    onFilter: (query: String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = query,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Theme.colors.onBackground,
            backgroundColor = spaceGray,
            cursorColor = Theme.colors.onBackground,
            focusedBorderColor = Theme.colors.onBackground,
            unfocusedBorderColor = spaceGray
        ),
        trailingIcon = {
            Icon(
                tint = Theme.colors.onBackground,
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