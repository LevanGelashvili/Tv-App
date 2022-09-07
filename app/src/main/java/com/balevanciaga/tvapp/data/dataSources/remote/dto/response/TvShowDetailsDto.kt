package com.balevanciaga.tvapp.data.dataSources.remote.dto.response

import com.balevanciaga.tvapp.custom.managers.DateManager
import com.balevanciaga.tvapp.data.dataSources.remote.api.ApiEndpoints
import com.balevanciaga.tvapp.domain.model.TvShowDetails
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
) {
    fun toDomain(): TvShowDetails = TvShowDetails(
        id = id,
        name = name,
        tagline = tagline,
        overview = overview,
        rating = rating,
        firstAirDate = DateManager.strToDate(dateStr = firstAirDate),
        lastAirDate = DateManager.strToDate(dateStr = lastAirDate),
        genres = genres.map { it.name },
        numSeasons = numSeasons,
        numEpisodes = numEpisodes,
        createdBy = creators.map { it.name },
        backdropUrl = if (backdropUrl == null) {
            null
        } else {
            ApiEndpoints.IMAGE_BACKDROP_URL + backdropUrl
        }
    )
}

data class GenreDto(
    @Json(name = "name") val name: String
)

data class CreatorDto(
    @Json(name = "name") val name: String
)