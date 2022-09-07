package com.balevanciaga.tvapp.presentation.list.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.main.ui.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowItem(
    show: TvShowBrief,
    onShowClicked: (id: Int) -> Unit
) {
    Card(
        modifier = Modifier.padding(all = 8.dp),
        shape = Theme.shapes.roundedDefault,
        elevation = 2.dp,
        onClick = {
            onShowClicked(show.id)
        }
    ) {
        Column(
            modifier = Modifier
                .background(color = Theme.colors.background)
                .fillMaxWidth()
                .height(300.dp)
        ) {
            TvShowImageWithGradient(imageUrl = show.backdropUrl)
            TvShowInfo(show = show)
        }
    }
}

@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    radius: Dp = 25.dp,
    strokeWidth: Dp = 2.dp
) {
    Box(
        modifier = modifier.size(radius * 2f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = when (rating) {
                    in 8f..10f -> Color.Green
                    in 5f..8f -> Color.Yellow
                    else -> Color.Red
                },
                startAngle = -90f,
                sweepAngle = 360f * rating / 10,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = rating.toString(),
            color = Theme.colors.onBackground,
            style = Theme.typography.bold16
        )
    }
}

@Composable
private fun TvShowInfo(
    show: TvShowBrief
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = show.name,
                color = Theme.colors.onBackground,
                style = Theme.typography.medium14,
            )
            Text(
                text = show.airDate?.year.toString(),
                color = Theme.colors.onBackground,
                style = Theme.typography.normal12,
            )
        }
        RatingBar(
            modifier = Modifier.padding(end = 8.dp),
            rating = show.rating
        )
    }
}

@Composable
private fun TvShowImageWithGradient(
    imageUrl: String?
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            0.5f to Color.Transparent,
                            1f to Color.Black
                        )
                    )
                }
            },
        painter = rememberAsyncImagePainter(
            model = imageUrl ?: "https://i.imgur.com/RLTSzOZ.png"
        ),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}