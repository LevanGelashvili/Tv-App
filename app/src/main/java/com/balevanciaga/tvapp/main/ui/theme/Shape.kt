package com.balevanciaga.tvapp.main.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

internal val LocalShapes = staticCompositionLocalOf { Shape() }

data class Shape(
    val roundedDefault: RoundedCornerShape = RoundedCornerShape(size = 10.dp),
    val roundedBackdropImage: RoundedCornerShape = RoundedCornerShape(size = 20.dp)
)