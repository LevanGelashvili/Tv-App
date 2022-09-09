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
    val normal12: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = netflixSans
    ),
    val normal14: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = netflixSans
    ),
    val medium10: TextStyle = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = netflixSans
    ),
    val medium14: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = netflixSans
    ),
    val bold10: TextStyle = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = netflixSans
    ),
    val bold16: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = netflixSans
    ),
    val black20: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Black,
        fontFamily = netflixSans
    )
)

val netflixSans = FontFamily(
    Font(R.font.netflix_sans_thin, weight = FontWeight.Thin),
    Font(R.font.netflix_sans_light, weight = FontWeight.Light),
    Font(R.font.netflix_sans_regular, weight = FontWeight.Normal),
    Font(R.font.netflix_sans_medium, weight = FontWeight.Medium),
    Font(R.font.netflix_sans_bold, weight = FontWeight.Bold),
    Font(R.font.netflix_sans_black, weight = FontWeight.Black),
    Font(R.font.netflix_sans_icon),
)
