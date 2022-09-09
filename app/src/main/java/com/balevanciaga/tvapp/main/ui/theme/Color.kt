package com.balevanciaga.tvapp.main.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = staticCompositionLocalOf { Color() }

data class Color(
    val primary: Color = tiffanyBlue,
    val secondary: Color = cgBlue,

    val onPrimary: Color = Color.White,
    val onSecondary: Color = Color.White,

    val background: Color = Color.Black,
    val backgroundVariant: Color = spaceGray,
    val onBackgroundDark: Color = textGray,
    val onBackground: Color = Color.White,

    val error: Color = coral,
    val yellow: Color = maxYellow
)

val tiffanyBlue = Color(0xFF17C3B2)
val cgBlue = Color(0xFF227C9D)
val maxYellow = Color(0xFFFFCB77)
val coral = Color(0xFFFE6D73)
val spaceGray = Color(0xFF23282C)
val textGray = Color(0xFFA3A3A3)
