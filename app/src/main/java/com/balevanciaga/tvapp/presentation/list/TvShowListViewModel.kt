package com.balevanciaga.tvapp.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.balevanciaga.tvapp.custom.base.BaseViewModel
import com.balevanciaga.tvapp.custom.data.paginator.Paginator
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.domain.repository.ITvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val tvShowRepository: ITvShowRepository
) : BaseViewModel<TvShowListAction, TvShowListViewState>() {

    override var viewState: TvShowListViewState by mutableStateOf(TvShowListViewState())

    private val paginator = Paginator(
        initialKey = viewState.page,
        onLoadUpdated = {
            viewModelScope.launch {
                viewState = viewState.copy(isLoading = it)
            }
        },
        onRequest = { nextPage ->
            tvShowRepository.getPopularShows(page = nextPage)
        },
        getNextKey = {
            viewState.page + 1
        },
        onSuccess = { items, newKey ->
            viewState = viewState.copy(
                tvShows = viewState.tvShows + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    private var cachedTvShows = listOf<TvShowBrief>()
    private var isSearchStarting = true

    init {
        postAction(TvShowListAction.LoadMore)
    }

    override fun onActionReceived(action: TvShowListAction) {
        when (action) {
            TvShowListAction.LoadMore -> {
                execute {
                    paginator.loadNextItems()
                }
            }
            is TvShowListAction.OnFilter -> {
                filterShows()
            }
        }
    }

    private fun filterShows() {
        val listToSearch = when (isSearchStarting) {
            true -> viewState.tvShows
            false -> cachedTvShows
        }
        execute(coroutineDispatcher = Dispatchers.Default) {
            if (viewState.query.isEmpty()) {
                isSearchStarting = true
                viewState = viewState.copy(
                    tvShows = cachedTvShows,
                    isSearching = false
                )
            } else {
                val result = listToSearch.filter {
                    it.name.contains(viewState.query, ignoreCase = true)
                }
                if (isSearchStarting) {
                    cachedTvShows = viewState.tvShows
                    isSearchStarting = false
                }
                viewState = viewState.copy(
                    tvShows = result,
                    isSearching = true
                )
            }
        }
    }
}