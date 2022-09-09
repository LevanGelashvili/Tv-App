package com.balevanciaga.tvapp.data.repository

import com.balevanciaga.tvapp.custom.data.apiHelper.ApiHelper
import com.balevanciaga.tvapp.data.dataSource.remote.api.TvShowApi
import com.balevanciaga.tvapp.data.mapper.TvShowMapper
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.domain.model.TvShowDetails
import com.balevanciaga.tvapp.domain.repository.ITvShowRepository
import javax.inject.Inject

class TvShowRepository @Inject constructor(
    private val api: TvShowApi,
) : ITvShowRepository {

    override suspend fun getPopularShows(page: Int): List<TvShowBrief> {
        return ApiHelper.makeApiCall {
            api.getPopularShows(page = page)
        }.results.map {
            TvShowMapper.toDomain(tvShowBriefDto = it)
        }
    }

    override suspend fun getShowDetails(id: Int): TvShowDetails {
        return TvShowMapper.toDomain(
            tvShowDetailsDto = ApiHelper.makeApiCall {
                api.getShowDetails(id = id)
            }
        )
    }

    override suspend fun getSimilarShows(id: Int): List<TvShowBrief> {
        return ApiHelper.makeApiCall {
            api.getSimilarShows(id = id)
        }.results
            .filter { it.id != id }
            .map {
                TvShowMapper.toDomain(tvShowBriefDto = it)
            }
    }
}