package com.balevanciaga.tvapp.presentation.details.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.balevanciaga.tvapp.main.ui.theme.Theme

@Composable
fun GenreItem(
    genre: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Theme.colors.primary,
                shape = RoundedCornerShape(size = 100.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = genre,
            color = Theme.colors.onBackground,
            style = Theme.typography.medium10
        )
    }
}