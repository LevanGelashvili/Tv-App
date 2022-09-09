package com.balevanciaga.tvapp.presentation.list.composables

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.balevanciaga.tvapp.custom.ext.limitLength
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
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TvShowImageWithGradient(imageUrl = show.posterUrl)
            Spacer(modifier = Modifier.height(8.dp))
            TvShowInfo(show = show)
        }
    }
}

@Composable
private fun TvShowImageWithGradient(
    imageUrl: String?
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            0.9f to Color.Transparent,
                            1.1f to Color.Black
                        )
                    )
                }
            },
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun TvShowInfo(
    show: TvShowBrief
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = show.name.limitLength(maxChars = 25),
            color = Theme.colors.onBackground,
            style = Theme.typography.medium10,
        )
        Text(
            modifier = Modifier.padding(end = 4.dp),
            text = "${show.rating}",
            color = Theme.colors.onBackground,
            style = Theme.typography.medium10,
        )
    }
}
