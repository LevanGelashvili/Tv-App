package com.balevanciaga.tvapp.data.dataSources.remote.dto.response

import com.balevanciaga.tvapp.custom.managers.DateManager
import com.balevanciaga.tvapp.data.dataSources.remote.api.ApiEndpoints
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.squareup.moshi.Json

data class TvShowBriefDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "vote_average") val rating: Double,
    @Json(name = "first_air_date") val date: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "backdrop_path") val backdropUrl: String,
) {
    fun toDomain(): TvShowBrief = TvShowBrief(
        id = id,
        name = name,
        rating = rating,
        airDate = DateManager.strToDate(dateStr = date),
        backdropUrl = ApiEndpoints.IMAGE_BACKDROP_URL + backdropUrl
    )
}
