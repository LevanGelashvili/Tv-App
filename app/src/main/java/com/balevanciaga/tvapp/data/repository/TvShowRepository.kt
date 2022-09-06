package com.balevanciaga.tvapp.data.repository

import com.balevanciaga.tvapp.custom.data.apiHelper.ApiHelper
import com.balevanciaga.tvapp.data.dataSources.local.room.dao.TvGenresDao
import com.balevanciaga.tvapp.data.dataSources.remote.api.TvShowApi
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.domain.model.TvShowDetails
import com.balevanciaga.tvapp.domain.repository.ITvShowRepository
import javax.inject.Inject

class TvShowRepository @Inject constructor(
    private val api: TvShowApi,
    private val genresDao: TvGenresDao
) : ITvShowRepository {

    override suspend fun getPopularShows(page: Int): List<TvShowBrief> {
        return ApiHelper.makeApiCall {
            api.getPopularShows(page = page)
        }.results.map {
            it.toDomain(genres = emptyList())
        }
    }

    override suspend fun getShowDetails(id: Int): TvShowDetails {
        return ApiHelper.makeApiCall {
            api.getShowDetails(id = id)
        }.toDomain()
    }
}