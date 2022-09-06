package com.balevanciaga.tvapp.main.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.balevanciaga.tvapp.R

internal val LocalTypography = staticCompositionLocalOf { Typography() }

data class Typography internal constructor(
    val medium14: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = netflixSans
    )
)

val netflixSans = FontFamily(
    Font(R.font.netflix_sans_black),
    Font(R.font.netflix_sans_bold),
    Font(R.font.netflix_sans_icon),
    Font(R.font.netflix_sans_light),
    Font(R.font.netflix_sans_medium),
    Font(R.font.netflix_sans_regular),
    Font(R.font.netflix_sans_thin),
)
