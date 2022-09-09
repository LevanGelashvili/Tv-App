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

    private var cachedTvShows = listOf<TvShowBrief>()
    private var isSearchStarting = true
    private var page = 1

    private val paginator = Paginator(
        initialKey = page,
        onLoadUpdated = {
            viewModelScope.launch {
                viewState = viewState.copy(isLoading = it)
            }
        },
        onRequest = { nextPage ->
            tvShowRepository.getPopularShows(page = nextPage)
        },
        getNextKey = {
            page + 1
        },
        onSuccess = { items, newKey ->
            if (cachedTvShows.isEmpty()) {
                cachedTvShows = items
            }
            page = newKey
            viewState = viewState.copy(
                initialLoading = false,
                tvShows = viewState.tvShows + items,
                endReached = items.isEmpty()
            )
        }
    )

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
                filterShows(query = action.query.trim())
            }
        }
    }

    private fun filterShows(query: String) {
        val listToSearch = when (isSearchStarting) {
            true -> viewState.tvShows
            false -> cachedTvShows
        }
        execute(coroutineDispatcher = Dispatchers.Default) {
            if (query.isBlank()) {
                isSearchStarting = true
                viewState = viewState.copy(
                    tvShows = cachedTvShows,
                    isSearching = false
                )
            } else {
                val result = listToSearch.filter {
                    it.name.contains(query, ignoreCase = true)
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