package com.balevanciaga.tvapp.data.dataSources.remote.dto.response

import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.squareup.moshi.Json

data class TvShowBriefDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "vote_average") val rating: Double,
    @Json(name = "poster_path") val posterUrl: String,
) {
    fun toDomain(): TvShowBrief = TvShowBrief(
        id = id,
        name = name,
        rating = rating,
        posterUrl = posterUrl
    )
}
