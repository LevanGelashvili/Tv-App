package com.balevanciaga.tvapp.main.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object Theme {

    val colors: Color
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val shapes: Shape
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun TvAppTheme(
    colors: Color = Theme.colors,
    shapes: Shape = Theme.shapes,
    typography: Typography = Theme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        content()
    }
}