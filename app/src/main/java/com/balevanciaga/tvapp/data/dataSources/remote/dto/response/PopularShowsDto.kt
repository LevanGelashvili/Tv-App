package com.balevanciaga.tvapp.data.dataSources.remote.dto.response

import com.squareup.moshi.Json

data class PopularShowsDto(
    @Json(name = "results") val results: List<TvShowBriefDto>
)