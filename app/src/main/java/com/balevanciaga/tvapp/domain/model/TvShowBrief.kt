package com.balevanciaga.tvapp.domain.model

import java.time.LocalDate

data class TvShowBrief(
    val id: Int,
    val name: String,
    val rating: Double,
    val airDate: LocalDate?,
    val backdropUrl: String,
)
