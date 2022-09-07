package com.balevanciaga.tvapp.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.balevanciaga.tvapp.custom.composables.Loader
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.main.ui.theme.Theme
import com.balevanciaga.tvapp.presentation.destinations.TvShowDetailsScreenDestination
import com.balevanciaga.tvapp.presentation.list.composables.SearchBar
import com.balevanciaga.tvapp.presentation.list.composables.TvShowItem
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