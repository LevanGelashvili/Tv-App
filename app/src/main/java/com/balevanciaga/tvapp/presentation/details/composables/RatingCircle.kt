package com.balevanciaga.tvapp.presentation.details.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.balevanciaga.tvapp.main.ui.theme.Theme
import com.balevanciaga.tvapp.main.ui.theme.coral
import com.balevanciaga.tvapp.main.ui.theme.maxYellow
import com.balevanciaga.tvapp.main.ui.theme.tiffanyBlue

@Composable
fun RatingCircle(
    modifier: Modifier = Modifier,
    rating: Float,
    radius: Dp = 20.dp,
    strokeWidth: Dp = 3.dp
) {
    Box(
        modifier = modifier.size(radius * 2f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = when (rating) {
                    in 8f..10f -> tiffanyBlue
                    in 5f..8f -> maxYellow
                    else -> coral
                },
                startAngle = -90f,
                sweepAngle = 360f * rating / 10,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = String.format("%.1f", rating),
            color = Theme.colors.onBackground,
            style = Theme.typography.bold10
        )
    }
}