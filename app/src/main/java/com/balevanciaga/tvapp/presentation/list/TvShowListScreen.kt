package com.balevanciaga.tvapp.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
        TvShowListContent(
            tvShows = tvShows,
            endReached = endReached,
            isLoading = isLoading,
            isSearching = isSearching,
            loadMore = {
                viewModel.postAction(TvShowListAction.LoadMore)
            },
            onFilter = {
                viewModel.postAction(TvShowListAction.OnFilter(query = it))
            },
            onShowClicked = {
                navigator.navigate(TvShowDetailsScreenDestination(id = it))
            }
        )
    }
}

@Composable
private fun TvShowListContent(
    tvShows: List<TvShowBrief>,
    endReached: Boolean,
    isLoading: Boolean,
    isSearching: Boolean,
    loadMore: () -> Unit,
    onFilter: (query: String) -> Unit,
    onShowClicked: (id: Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SearchBar(onFilter = onFilter)
        }

        items(tvShows.size) { i ->
            if (i >= tvShows.size - 1 && !endReached && !isLoading && !isSearching) {
                loadMore()
            }
            TvShowItem(
                show = tvShows[i],
                onShowClicked = onShowClicked
            )
        }

        if (isLoading) {
            item {
                BottomLoader()
            }
        }
    }
}

@Composable
private fun SearchBar(
    onFilter: (query: String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = query,
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
            Image(
                modifier = Modifier
                    .clip(shape = Theme.shapes.roundedDefault)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter(model = show.backdropUrl),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = show.name,
                    color = Theme.colors.onBackground,
                    style = Theme.typography.medium14,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(alignment = Alignment.TopStart)
                )
            }
        }
    }
}

@Composable
private fun BottomLoader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}