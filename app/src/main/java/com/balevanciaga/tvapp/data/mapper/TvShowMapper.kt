package com.balevanciaga.tvapp.data.mapper

import com.balevanciaga.tvapp.custom.managers.DateManager
import com.balevanciaga.tvapp.data.dataSource.remote.api.ApiEndpoints
import com.balevanciaga.tvapp.data.dataSource.remote.dto.response.TvShowBriefDto
import com.balevanciaga.tvapp.data.dataSource.remote.dto.response.TvShowDetailsDto
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.domain.model.TvShowDetails

object TvShowMapper {

    fun toDomain(tvShowBriefDto: TvShowBriefDto): TvShowBrief = with(tvShowBriefDto) {
        TvShowBrief(
            id = id,
            name = name,
            rating = rating,
            firstAirDate = DateManager.strToDate(dateStr = firstAirDate),
            posterUrl = posterUrl.formatImageUrl(prefix = ApiEndpoints.IMAGE_POSTER_PREFIX)
        )
    }

    fun toDomain(tvShowDetailsDto: TvShowDetailsDto): TvShowDetails = with(tvShowDetailsDto) {
        TvShowDetails(
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
            backdropUrl = backdropUrl.formatImageUrl(prefix = ApiEndpoints.IMAGE_BACKDROP_PREFIX)
        )
    }

    private fun String?.formatImageUrl(prefix: String): String? {
        return if (this == null) {
            null
        } else {
            prefix + this
        }
    }
}