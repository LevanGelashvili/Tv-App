package com.balevanciaga.tvapp.data.dataSource.remote.dto.response

import com.squareup.moshi.Json

data class TvShowDetailsDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "vote_average") val rating: Float,
    @Json(name = "first_air_date") val firstAirDate: String,
    @Json(name = "last_air_date") val lastAirDate: String,
    @Json(name = "backdrop_path") val backdropUrl: String?,
    @Json(name = "overview") val overview: String,
    @Json(name = "genres") val genres: List<GenreDto>,
    @Json(name = "number_of_seasons") val numSeasons: Int,
    @Json(name = "number_of_episodes") val numEpisodes: Int,
    @Json(name = "created_by") val creators: List<CreatorDto>,
)

data class GenreDto(
    @Json(name = "name") val name: String
)

data class CreatorDto(
    @Json(name = "name") val name: String
)