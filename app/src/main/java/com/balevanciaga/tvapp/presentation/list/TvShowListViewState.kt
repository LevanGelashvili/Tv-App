package com.balevanciaga.tvapp.presentation.list

import com.balevanciaga.tvapp.domain.model.TvShowBrief

data class TvShowListViewState(
    val tvShows: List<TvShowBrief> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = true,
    val isSearching: Boolean = false,

    // Pagination variables
    val page: Int = 1,
    val endReached: Boolean = false
)
