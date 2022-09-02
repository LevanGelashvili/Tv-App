package com.balevanciaga.tvapp.data.repository

import com.balevanciaga.tvapp.custom.data.apiHelper.ApiHelper
import com.balevanciaga.tvapp.data.dataSources.remote.api.TvShowApi
import com.balevanciaga.tvapp.domain.model.TvShow
import com.balevanciaga.tvapp.domain.repository.ITvShowRepository
import javax.inject.Inject

class TvShowRepository @Inject constructor(
    private val api: TvShowApi
) : ITvShowRepository {

    override suspend fun getPopularShows(): List<TvShow> {
        return ApiHelper.makeApiCall {
            api.getPopularShows()
        }.results.map { it.toDomain() }
    }

    override suspend fun getShowsByQuery(query: String): List<TvShow> {
        TODO("Not yet implemented")
    }
}