package com.balevanciaga.tvapp.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.balevanciaga.tvapp.custom.composables.Loader
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.main.ui.theme.Theme
import com.balevanciaga.tvapp.presentation.destinations.TvShowDetailsScreenDestination
import com.balevanciaga.tvapp.presentation.list.animation.TvShowListScreenAnimation
import com.balevanciaga.tvapp.presentation.list.composables.SearchBar
import com.balevanciaga.tvapp.presentation.list.composables.TvShowItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination(style = TvShowListScreenAnimation::class)
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
                query = query,
                canLoadMore = !endReached && !isLoading && !isSearching,
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
}


@Composable
private fun TvShowListContent(
    tvShows: List<TvShowBrief>,
    query: String,
    canLoadMore: Boolean,
    loadMore: () -> Unit,
    onFilter: (query: String) -> Unit,
    onShowClicked: (id: Int) -> Unit,
    gridCellCount: Int = 2,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        SearchBar(
            modifier = Modifier
                .background(color = Theme.colors.background)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            query = query,
            onFilter = onFilter
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = gridCellCount)
        ) {
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
}