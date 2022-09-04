package com.balevanciaga.tvapp.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.presentation.destinations.TvShowDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private const val CELL_COUNT = 2
private val fullWidthSpan: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }

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
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = CELL_COUNT),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        item(span = fullWidthSpan) {
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
            item(span = fullWidthSpan) {
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

@Composable
private fun TvShowItem(
    show: TvShowBrief,
    onShowClicked: (id: Int) -> Unit
) {
    Text(
        modifier = Modifier
            .padding(all = 25.dp)
            .padding(vertical = 35.dp)
            .clickable {
                onShowClicked(show.id)
            },
        text = show.name,
        textAlign = TextAlign.Center
    )
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