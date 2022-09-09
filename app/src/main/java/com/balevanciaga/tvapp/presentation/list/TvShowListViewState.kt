package com.balevanciaga.tvapp.presentation.list

import com.balevanciaga.tvapp.domain.model.TvShowBrief

data class TvShowListViewState(
    val tvShows: List<TvShowBrief> = emptyList(),
    val query: String = "",
    val initialLoading: Boolean = true,
    val isLoading: Boolean = true,
    val isSearching: Boolean = false,
    val endReached: Boolean = false
)
