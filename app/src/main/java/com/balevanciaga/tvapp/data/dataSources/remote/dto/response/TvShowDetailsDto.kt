package com.balevanciaga.tvapp.data.dataSources.remote.dto.response

import com.balevanciaga.tvapp.domain.model.TvShowDetails
import com.squareup.moshi.Json

data class TvShowDetailsDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
) {
    fun toDomain(): TvShowDetails = TvShowDetails(
        id = id,
        name = name
    )
}