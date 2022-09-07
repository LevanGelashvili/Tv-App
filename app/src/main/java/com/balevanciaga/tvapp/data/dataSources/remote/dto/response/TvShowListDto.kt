package com.balevanciaga.tvapp.data.dataSources.remote.dto.response

import com.balevanciaga.tvapp.custom.managers.DateManager
import com.balevanciaga.tvapp.data.dataSources.remote.api.ApiEndpoints
import com.balevanciaga.tvapp.domain.model.TvShowBrief
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
) {
    fun toDomain(): TvShowBrief = TvShowBrief(
        id = id,
        name = name,
        rating = rating,
        firstAirDate = DateManager.strToDate(dateStr = firstAirDate),
        posterUrl = if (posterUrl == null) {
            null
        } else {
            ApiEndpoints.IMAGE_POSTER_URL + posterUrl
        }
    )
}