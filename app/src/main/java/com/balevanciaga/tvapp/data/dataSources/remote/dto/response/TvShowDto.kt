package com.balevanciaga.tvapp.data.dataSources.remote.dto.response

import com.balevanciaga.tvapp.domain.model.TvShow
import com.squareup.moshi.Json

data class TvShowDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "vote_average") val rating: Double,
    @Json(name = "poster_path") val posterUrl: String,
) {
    fun toDomain(): TvShow = TvShow(
        id = id,
        name = name,
        rating = rating,
        posterUrl = posterUrl
    )
}
