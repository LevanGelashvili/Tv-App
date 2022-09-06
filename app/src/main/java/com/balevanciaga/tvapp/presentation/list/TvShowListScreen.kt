package com.balevanciaga.tvapp.presentation.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.main.ui.theme.Theme
import com.balevanciaga.tvapp.presentation.destinations.TvShowDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun TvShowListScreen(
    navigator: DestinationsNavigator,
    viewModel: TvShowListViewModel = hiltViewModel()
) {
    with(viewModel.viewState) {
        if (initialLoading) {
            Loader()
        } else {
            TvShowListContent(
                tvShows = tvShows,
                canLoadMore = !endReached && !isLoading && !isSearching,
                loadMore = {
                    viewModel.postAction(TvShowListAction.LoadMore)
                },
                onFilter = {
                    viewModel.postAction(TvShowListAction.OnFilter(query = it))
                }
            ) {
                navigator.navigate(TvShowDetailsScreenDestination(id = it))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TvShowListContent(
    tvShows: List<TvShowBrief>,
    canLoadMore: Boolean,
    loadMore: () -> Unit,
    onFilter: (query: String) -> Unit,
    onShowClicked: (id: Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            Column(
                modifier = Modifier
                    .background(color = Theme.colors.background)
                    .padding(8.dp)
            ) {
                SearchBar(onFilter = onFilter)
            }
        }
        items(tvShows.size) { i ->
            if (i >= tvShows.size - 1 && canLoadMore) {
                loadMore()
            }
            TvShowItem(
                show = tvShows[i],
                onShowClicked = onShowClicked
            )
        }
    }
}

@Composable
private fun SearchBar(
    onFilter: (query: String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = query,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Theme.colors.onBackground,
            unfocusedBorderColor = Theme.colors.primary,
            focusedBorderColor = Theme.colors.secondary,
            cursorColor = Theme.colors.secondary
        ),
        trailingIcon = {
            Icon(
                tint = Theme.colors.primary,
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TvShowItem(
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
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
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

@Composable
private fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Theme.colors.primary,
        )
    }
}