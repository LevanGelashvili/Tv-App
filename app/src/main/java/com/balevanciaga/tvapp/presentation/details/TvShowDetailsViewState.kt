package com.balevanciaga.tvapp.presentation.details

import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.domain.model.TvShowDetails

data class TvShowDetailsViewState(
    val details: TvShowDetails? = null,
    val similarShows: List<TvShowBrief> = emptyList(),
    val initialLoading: Boolean = true
)
