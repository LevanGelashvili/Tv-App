package com.balevanciaga.tvapp.domain.model

import java.time.LocalDate

data class TvShowDetails(
    val id: Int,
    val name: String,
    val backdropUrl: String?,
    val tagline: String,
    val firstAirDate: LocalDate?,
    val lastAirDate: LocalDate?,
    val rating: Float,
    val overview: String,
    val genres: List<String>,
    val numSeasons: Int,
    val numEpisodes: Int,
    val createdBy: List<String>
)
