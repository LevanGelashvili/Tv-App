package com.balevanciaga.tvapp.data.dataSource.remote.dto.response

import com.squareup.moshi.Json

data class TvShowListDto(
    @Json(name = "results") val results: List<TvShowBriefDto>
)

data class TvShowBriefDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "vote_average") val rating: Float,
    @Json(name = "first_air_date") val firstAirDate: String,
    @Json(name = "backdrop_path") val backdropUrl: String?,
    @Json(name = "poster_path") val posterUrl: String?,
)