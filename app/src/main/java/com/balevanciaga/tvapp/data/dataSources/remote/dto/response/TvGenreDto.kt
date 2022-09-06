package com.balevanciaga.tvapp.data.dataSources.remote.dto.response

import com.balevanciaga.tvapp.domain.model.TvGenre
import com.squareup.moshi.Json

data class TvGenreDto(
    @Json(name = "id") val id: Int,
    @Json(name = "id") val name: String,
) {
    fun toDomain(): TvGenre = TvGenre(
        id = id,
        name = name
    )
}
