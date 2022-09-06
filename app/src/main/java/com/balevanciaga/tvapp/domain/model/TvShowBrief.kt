package com.balevanciaga.tvapp.domain.model

import java.time.LocalDate

data class TvShowBrief(
    val id: Int,
    val name: String,
    val rating: Float,
    val genres: List<TvGenre> = emptyList(),
    val airDate: LocalDate?,
    val backdropUrl: String?,
)
